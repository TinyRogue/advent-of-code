package day1;

import java.util.Collection;
import java.util.HashMap;

public class SimilarityChecker {
    public static Long score(Collection<Integer> a, Collection<Integer> b) {
        var similarity = 0L;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : b) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : a) {
            if (!map.containsKey(i)) {
                continue;
            }
            similarity += (long) i * map.get(i);
        }
        return similarity;
    }
}
