package com.github.tinyrogue.day6;

import java.util.*;

public class GuardMovementPredictor {

    private static Map<Direction, Set<Point>> getTraversedPath(final String[] map) {
        Point position = getStartingPosition(map);
        if (position == null) {
            throw new IllegalArgumentException("No start position found");
        }

        var currentDirection = Direction.NORTH;
        var stepsTaken = new HashMap<Direction, Set<Point>>(Map.of(
                Direction.NORTH, new HashSet<>(Set.of(position)),
                Direction.EAST, new HashSet<>(),
                Direction.SOUTH, new HashSet<>(),
                Direction.WEST, new HashSet<>()
        ));

        while (true) {
            var nextStep = position.move(currentDirection);
            if (isEnd(nextStep, map)) return stepsTaken;
            if (!isLegal(nextStep, map)) {
                currentDirection = nextDirection(currentDirection);
                continue;
            }
            if (isLooped(stepsTaken, currentDirection, nextStep)) throw new LoopException();
            position = nextStep;
            stepsTaken.get(currentDirection).add(position);
        }
    }

    public static long pathTravelledLength(final String[] map) {
        return getTraversedPath(map).values().stream().flatMap(Collection::stream).distinct().count();
    }

    public static int countLoopingObstructions(String[] map) {
        var traversedPath = getTraversedPath(map).values().stream().flatMap(Collection::stream).filter(el -> !el.equals(getStartingPosition(map))).distinct().toList();

        var obstacleLoop = 0;
        for (var point : traversedPath) {
            var editableMap = Arrays.copyOf(map, map.length);
            editableMap[point.y()] = editableMap[point.y()].substring(0, point.x()) + "#" + editableMap[point.y()].substring(point.x() + 1);
            try {
                getTraversedPath(editableMap);
            } catch (LoopException e) {
                obstacleLoop++;
            }
        }
        return obstacleLoop;
    }

    private static Direction nextDirection(final Direction currentDirection) {
        return switch (currentDirection) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    private static Point getStartingPosition(final String[] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length(); x++) {
                if (map[y].charAt(x) == '^') {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    private static boolean isEnd(final Point point, final String[] map) {
        return point.x() < 0 || point.x() >= map[0].length() || point.y() < 0 || point.y() >= map.length;
    }

    private static boolean isLegal(final Point point, final String[] map) {
        return !isEnd(point, map) && map[point.y()].charAt(point.x()) != '#';
    }

    private static boolean isLooped(final Map<Direction, Set<Point>> visited, final Direction nextDir, final Point nextStep) {
        return visited.getOrDefault(nextDir, new HashSet<>()).contains(nextStep);
    }
}
