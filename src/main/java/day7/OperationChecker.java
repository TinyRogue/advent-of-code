package day7;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationChecker {

    public static boolean isFeasible(Long result, List<Long> elements) {
        Set<Long> subResults = new HashSet<>(Set.of(elements.getFirst()));
        var omitFirst = elements.stream().skip(1).toList();
        for (var element : omitFirst) {
            subResults = subResults.stream().<Long>mapMulti((el, consumer) -> {
                consumer.accept(el + element);
                consumer.accept(el * element);
            }).collect(Collectors.toSet());
        }
        return subResults.contains(result);
    }

    public static long calibrationValue(List<Map.Entry<Long, List<Long>>> ops) {
        return ops.stream()
                .filter(entry -> OperationChecker.isFeasible(entry.getKey(), entry.getValue()))
                .map(Map.Entry::getKey)
                .reduce(0L, Long::sum);

    }
}
