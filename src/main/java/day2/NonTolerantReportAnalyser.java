package day2;

import java.util.Collection;

public class NonTolerantReportAnalyser extends ReportAnalyser {
    public boolean isSafe(Collection<Integer> levels) {
        if (levels == null) throw new IllegalArgumentException("levels cannot be null");
        if (levels.size() < 3) return true;

        final var iter = levels.iterator();
        var previous = iter.next();
        var current = iter.next();
        if (isFar(previous, current)) return false;
        final var increasing = previous < current;
        while (iter.hasNext()) {
            previous = current;
            current = iter.next();
            if (isFar(previous, current) || isIncreasing(previous, current) != increasing) {
                return false;
            }
        }
        return true;
    }
}
