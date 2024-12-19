package com.github.tinyrogue.day7.operators;

public class Multiple implements Operator {
    @Override
    public Long apply(Long a, Long b) {
        return a * b;
    }
}
