package com.github.tinyrogue.day7;

import com.github.tinyrogue.day7.operators.Operator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationChecker {

    public static boolean isFeasible(final Long result, final List<Long> operations, final List<Operator> operators) {
        Set<Long> subResults = new HashSet<>(Set.of(operations.getFirst()));
        var omitFirst = operations.stream().skip(1).toList();
        for (var element : omitFirst) {
            subResults = subResults.stream().<Long>mapMulti(
                    (el, consumer) -> operators.forEach(
                            op -> consumer.accept(op.apply(el, element))
                    )
            ).collect(Collectors.toSet());
        }
        return subResults.contains(result);
    }

    public static long calibrationValue(final List<Map.Entry<Long, List<Long>>> operations, final List<Operator> operators) {
        return operations.stream()
                .filter(entry -> OperationChecker.isFeasible(entry.getKey(), entry.getValue(), operators))
                .map(Map.Entry::getKey)
                .reduce(0L, Long::sum);

    }
}