package com.github.tinyrogue.day7;

import com.github.tinyrogue.data.ResourceLoader;
import com.github.tinyrogue.day7.operators.Add;
import com.github.tinyrogue.day7.operators.Concat;
import com.github.tinyrogue.day7.operators.Multiple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorCheckerTest {
    private static final ResourceLoader resourceLoader = ResourceLoader.ofDay(7);
    private static final DataParser dataParser = new DataParser(resourceLoader.getTestPart1());

    public static Stream<Arguments> getSampleData() {
        return Stream.of(
                Arguments.of(true, Map.entry(190L, List.of(10L, 19L))),
                Arguments.of(true, Map.entry(3267L, List.of(81L, 40L, 27L))),
                Arguments.of(false, Map.entry(156L, List.of(15L, 6L))),
                Arguments.of(false, Map.entry(7290L, List.of(6L, 8L, 6L, 15L))),
                Arguments.of(false, Map.entry(161011L, List.of(16L, 10L, 13L))),
                Arguments.of(false, Map.entry(192L, List.of(17L, 8L, 14L))),
                Arguments.of(false, Map.entry(21037L, List.of(9L, 7L, 18L, 13L))),
                Arguments.of(true, Map.entry(292L, List.of(11L, 6L, 16L, 20L)))
        );
    }

    public static Stream<Arguments> getPart2SampleData() {
        return Stream.of(
                Arguments.of(true, Map.entry(190L, List.of(10L, 19L))),
                Arguments.of(true, Map.entry(3267L, List.of(81L, 40L, 27L))),
                Arguments.of(true, Map.entry(156L, List.of(15L, 6L))),
                Arguments.of(true, Map.entry(7290L, List.of(6L, 8L, 6L, 15L))),
                Arguments.of(false, Map.entry(161011L, List.of(16L, 10L, 13L))),
                Arguments.of(true, Map.entry(192L, List.of(17L, 8L, 14L))),
                Arguments.of(false, Map.entry(21037L, List.of(9L, 7L, 18L, 13L))),
                Arguments.of(true, Map.entry(292L, List.of(11L, 6L, 16L, 20L)))
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isFeasible(boolean expected, Map.Entry<Long, List<Long>> operations) {
        assertEquals(
                expected,
                OperationChecker.isFeasible(operations.getKey(), operations.getValue(), List.of(new Add(), new Multiple()))
        );
    }

    @ParameterizedTest
    @MethodSource("getPart2SampleData")
    void isFeasibleWithConcat(boolean expected, Map.Entry<Long, List<Long>> operations) {
        assertEquals(
                expected,
                OperationChecker.isFeasible(operations.getKey(), operations.getValue(), List.of(new Add(), new Multiple(), new Concat()))
        );
    }

    @Test
    void shouldCalculateCalibrationValue() {
        int expectedCalibrationValue = 3749;
        assertEquals(
                expectedCalibrationValue,
                OperationChecker.calibrationValue(dataParser.getOperations(), List.of(new Add(), new Multiple()))
        );
    }

    @Test
    void shouldCalculateCalibrationValueWithConcatenationOperation() {
        int expectedCalibrationValue = 11387;
        assertEquals(
                expectedCalibrationValue,
                OperationChecker.calibrationValue(dataParser.getOperations(), List.of(new Add(), new Multiple(), new Concat()))
        );
    }
}