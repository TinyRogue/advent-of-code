package com.github.tinyrogue.day5;


import java.util.*;
import java.util.stream.Collectors;

public class PrintQueueValidator {
    /**
     * A page (key) must go before all the pages in the list (value of the map)
     */
    private final Map<Integer, Set<Integer>> consecutivePages;

    /**
     * @param rules - "A|B", where A page must go before B page
     */
    public PrintQueueValidator(final String[] rules) {
        consecutivePages = Arrays.stream(rules).map(
                rule -> Arrays.stream(rule.split("\\|")).map(Integer::parseInt).toArray(Integer[]::new)
        ).collect(
                Collectors.groupingBy(
                        consecutiveEls -> consecutiveEls[0],
                        Collectors.mapping(consecutiveEls -> consecutiveEls[1], Collectors.toSet())
                )
        );
    }

    public static List<Integer> parseSequence(final String sequence) {
        return Arrays.stream(sequence.split(",")).map(Integer::parseInt).toList();
    }

    /**
     * @param sequence is the update sequence in format "pageA, pageB, pageC..."
     * @return true if the update sequence is aligning with the rules
     */
    public boolean isValid(final List<Integer> sequence) {
        var usedElements = new HashSet<Integer>();
        for (var element : sequence) {
            var mustGoBefore = consecutivePages.get(element);
            if (mustGoBefore != null && usedElements.stream().anyMatch(mustGoBefore::contains)) {
                return false;
            }
            usedElements.add(element);
        }
        return true;
    }

    public List<Integer> correctOrdering(final List<Integer> sequence) {
        var result = new ArrayList<>(sequence);
        result.sort((a, b) -> {
            var rules = consecutivePages.get(a);
            if (rules != null && rules.contains(b)) {
                return -1;
            }
            return 1;
        });
        return result;
    }
}
