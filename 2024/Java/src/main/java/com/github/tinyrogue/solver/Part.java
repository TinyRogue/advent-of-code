package com.github.tinyrogue.solver;

public enum Part {
    FIRST("first part"),
    SECOND("second part"),
    ;

    private final String description;

    Part(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
