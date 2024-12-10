package day7.operators;

public class Add implements Operator {
    @Override
    public Long apply(Long a, Long b) {
        return a + b;
    }
}
