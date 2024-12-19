package com.github.tinyrogue.solver;

public class InvalidDaySelection extends RuntimeException {
    public InvalidDaySelection(int day) {
        super("Advent of Code has 25 days! Day " + day + " was provided.");
    }
}
