package day6;

import java.util.HashSet;
import java.util.Set;

public class GuardMovementPredictor {

    public static long pathTravelledLength(final String[] map) {
        Point position = null;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length(); x++) {
                if (map[y].charAt(x) == '^') {
                    position = new Point(x, y);
                    break;
                }
            }
        }

        if (position == null) {
            throw new IllegalArgumentException("No start position found");
        }

        var stepsTaken = new HashSet<>(Set.of(position));
        var currentDirection = Direction.NORTH;
        while (position.y() >= 0 && position.y() < map.length && position.x() >= 0 && position.x() < map[0].length()) {
            var nextStep = position.move(currentDirection);
            if (isEnd(nextStep, map)) break;
            if (!isLegal(nextStep, map)) {
                currentDirection = switch (currentDirection) {
                    case NORTH -> Direction.EAST;
                    case EAST -> Direction.SOUTH;
                    case SOUTH -> Direction.WEST;
                    case WEST -> Direction.NORTH;
                };
                continue;
            }
            position = nextStep;
            stepsTaken.add(position);
        }

        return stepsTaken.size();
    }

    private static boolean isEnd(final Point point, final String[] map) {
        return point.x() < 0 || point.x() >= map[0].length() || point.y() < 0 || point.y() >= map.length;
    }

    private static boolean isLegal(final Point point, final String[] map) {
        return !isEnd(point, map) && map[point.y()].charAt(point.x()) != '#';
    }
}
