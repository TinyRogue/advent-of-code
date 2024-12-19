package com.github.tinyrogue.day1;

import java.util.Collection;

public class DistanceMeasure {
    public static int distanceBetween(int measureA, int measureB) {
        return Math.abs(measureA - measureB);
    }

    public static Long totalDistanceBetween(Collection<Integer> measuresA, Collection<Integer> measuresB) {
        var aIter = measuresA.stream().sorted().iterator();
        var bIter = measuresB.stream().sorted().iterator();
        var sum = 0L;
        while (aIter.hasNext() && bIter.hasNext()) {
            sum += distanceBetween(aIter.next(), bIter.next());
        }
        return sum;
    }
}
