package day6;

import java.util.HashSet;
import java.util.Set;

public class GuardMovementPredictor {

    public static long pathTravelledLength(final String[] map) {
        Point position = getStartingPosition(map);
        if (position == null) {
            throw new IllegalArgumentException("No start position found");
        }

        var stepsTaken = new HashSet<>(Set.of(position));
        var currentDirection = Direction.NORTH;
        while (true) {
            var nextStep = position.move(currentDirection);
            if (isEnd(nextStep, map)) break;
            if (!isLegal(nextStep, map)) {
                currentDirection = nextDirection(currentDirection);
                continue;
            }
            position = nextStep;
            stepsTaken.add(position);
        }

        return stepsTaken.size();
    }

    public static int countLoopingObstructions(String[] map) {
        return 0;
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
}
