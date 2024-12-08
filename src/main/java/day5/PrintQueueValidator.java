package day5;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    /**
     * @param ordering is the update sequence in format "pageA, pageB, pageC..."
     * @return true if the update sequence is aligning with the rules
     */
    public boolean isValid(final String ordering) {
        var updateSequence = Arrays.stream(ordering.split(",")).map(Integer::parseInt).toList();
        var usedElements = new HashSet<Integer>();
        for (var element : updateSequence) {
            var mustGoBefore = consecutivePages.get(element);
            if (mustGoBefore != null && usedElements.stream().anyMatch(mustGoBefore::contains)) {
                return false;
            }
            usedElements.add(element);
        }
        return true;
    }
}
