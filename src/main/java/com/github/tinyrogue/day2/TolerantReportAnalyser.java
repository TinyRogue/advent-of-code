package com.github.tinyrogue.day2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TolerantReportAnalyser extends ReportAnalyser {
    private static final NonTolerantReportAnalyser nonTolerantReportAnalyser = new NonTolerantReportAnalyser();

    public boolean isSafe(Collection<Integer> levels) {
        if (levels == null) throw new IllegalArgumentException("levels cannot be null");
        if (levels.size() < 3) return true;
        List<List<Integer>> subCollections = new ArrayList<>();
        for (int i = 0; i < levels.size(); i++) {
            var subCollection = new ArrayList<>(levels);
            subCollection.remove(i);
            subCollections.add(subCollection);
        }
        return subCollections.stream().anyMatch(nonTolerantReportAnalyser::isSafe);
    }
}
