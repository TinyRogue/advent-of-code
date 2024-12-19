package com.github.tinyrogue.day6;

public record Point(int x, int y) {
    public Point move(Direction dir) {
        return switch (dir) {
            case NORTH -> new Point(x, y - 1);
            case SOUTH -> new Point(x, y + 1);
            case EAST -> new Point(x + 1, y);
            case WEST -> new Point(x - 1, y);
        };
    }
}
