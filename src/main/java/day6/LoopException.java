package day6;

public class LoopException extends RuntimeException {
    public LoopException() {
        super("Loop detected");
    }
}
