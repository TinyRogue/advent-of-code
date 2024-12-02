package day2;

import java.util.Collection;
import java.util.List;

public class ReportAnalyser {

    public static boolean isSafe(Collection<Integer> levels) {
        if (levels == null) throw new IllegalArgumentException("levels cannot be null");
        if (levels.size() < 2) return true;

        var iter = levels.iterator();
        var previous = iter.next();
        var current = iter.next();
        if (isSkipping(previous, current)) return false;
        var increasing = previous < current;
        while (iter.hasNext()) {
            previous = current;
            current = iter.next();
            if (isSkipping(previous, current) || isIncreasing(previous, current) != increasing) {
                return false;
            }
        }
        return true;
    }

    public static Long getSafeReportsNumber(List<List<Integer>> reports) {
        return reports.stream().filter(ReportAnalyser::isSafe).count();
    }

    private static boolean isSkipping(int a, int b) {
        var diff = Math.abs(a - b);
        return diff < 1 || diff > 3;
    }

    private static boolean isIncreasing(int a, int b) {
        return a < b;
    }
}
