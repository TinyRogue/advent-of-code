package day8;

public record Position(int x, int y) {
    public Position offset(int horizontalOffset, int verticalOffset) {
        return new Position(x + horizontalOffset, y + verticalOffset);
    }
}
