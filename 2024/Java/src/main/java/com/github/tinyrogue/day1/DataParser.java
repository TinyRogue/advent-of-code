package com.github.tinyrogue.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataParser {
    private final List<Integer> firstLocationIds = new ArrayList<>();
    private final List<Integer> secondLocationIds = new ArrayList<>();

    public DataParser(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        var lines = input.lines().toList();
        for (String line : lines) {
            var parts = line.trim().split("\\s+");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Each line must contain at least two values");
            }

            try {
                firstLocationIds.add(Integer.parseInt(parts[0]));
                secondLocationIds.add(Integer.parseInt(parts[parts.length - 1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in line: " + line, e);
            }
        }
    }

    public List<Integer> getFirstLocationIds() {
        return Collections.unmodifiableList(firstLocationIds);
    }

    public List<Integer> getSecondLocationIds() {
        return Collections.unmodifiableList(secondLocationIds);
    }
}
