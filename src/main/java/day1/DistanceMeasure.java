package day1;

import java.util.Collection;

public class DistanceMeasure {
    public static int distanceBetween(int measureA, int measureB) {
        return Math.abs(measureA - measureB);
    }

    public static Long totalDistanceBetween(Collection<Integer> measureA, Collection<Integer> measureB) {
        var aIter = measureA.stream().sorted().iterator();
        var bIter = measureB.stream().sorted().iterator();
        var sum = 0L;
        while (aIter.hasNext() && bIter.hasNext()) {
            sum += distanceBetween(aIter.next(), bIter.next());
        }
        return sum;
    }
}
