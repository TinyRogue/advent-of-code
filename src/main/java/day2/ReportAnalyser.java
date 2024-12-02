package day2;

import java.util.Collection;
import java.util.List;

public abstract class ReportAnalyser {
    protected static boolean isFar(int a, int b) {
        var diff = Math.abs(a - b);
        return diff < 1 || diff > 3;
    }

    protected static boolean isIncreasing(int a, int b) {
        return a < b;
    }

    protected Long getSafeReportsNumber(List<List<Integer>> reports) {
        if (reports == null) {
            throw new IllegalArgumentException("reports cannot be null");
        }
        return reports.stream().filter(this::isSafe).count();
    }

    protected abstract boolean isSafe(Collection<Integer> report);
}
