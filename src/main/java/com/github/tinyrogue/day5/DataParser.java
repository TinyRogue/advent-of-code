package com.github.tinyrogue.day5;

public class DataParser {
    private final String[] rules;
    private final String[] updates;

    public DataParser(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        var data = input.split("\n\n");
        rules = data[0].split("\n");
        updates = data[1].split("\n");
    }

    public String[] getRules() {
        return rules;
    }

    public String[] getUpdates() {
        return updates;
    }
}
