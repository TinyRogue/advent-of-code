package com.github.tinyrogue.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataParser {
    private final List<List<Integer>> reports = new ArrayList<>();

    public DataParser(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        for (var reportData : input.lines().toList()) {
            var scanner = new Scanner(reportData);
            var report = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                report.add(scanner.nextInt());
            }
            reports.add(report);
        }
    }

    public List<List<Integer>> getReports() {
        return reports;
    }
}
