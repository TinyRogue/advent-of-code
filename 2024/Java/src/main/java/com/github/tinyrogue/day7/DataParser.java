package com.github.tinyrogue.day7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataParser {
    private final List<Map.Entry<Long, List<Long>>> operations;

    public DataParser(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        operations = input.lines()
                .map(operation -> {
                    String[] parts = operation.split(":");
                    Long opResult = Long.parseLong(parts[0].trim());
                    List<Long> opElements = Arrays.stream(parts[1].trim().split(" "))
                            .map(Long::parseLong)
                            .toList();
                    return Map.entry(opResult, opElements);
                })
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Long, List<Long>>> getOperations() {
        return operations;
    }
}
