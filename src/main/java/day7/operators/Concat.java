package day7.operators;

public class Concat implements Operator {
    @Override
    public Long apply(Long a, Long b) {
        return Long.parseLong(a.toString() + b.toString());
    }
}
