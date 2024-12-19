package com.github.tinyrogue.day5;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintQueueValidatorTest {
    private static final String data = ResourceLoader.ofDay(5).getTestPart1();
    private static final DataParser parser = new DataParser(data);
    private static final PrintQueueValidator printQueueValidator = new PrintQueueValidator(parser.getRules());


    private static Stream<Arguments> sampleTestData() {
        return Stream.of(
                Arguments.of(true, "75,47,61,53,29"),
                Arguments.of(true, "97,61,53,29,13"),
                Arguments.of(true, "75,29,13"),
                Arguments.of(false, "75,97,47,61,53"),
                Arguments.of(false, "61,13,29"),
                Arguments.of(false, "97,13,75,29,47")
        );
    }

    public static Stream<Arguments> correctOrderingData() {
        return Stream.of(
                Arguments.of("97,75,47,61,53", "75,97,47,61,53"),
                Arguments.of("61,29,13", "61,13,29"),
                Arguments.of("97,75,47,29,13", "97,13,75,29,47")
        );
    }

    @ParameterizedTest
    @MethodSource("sampleTestData")
    void shouldCheckValidity(boolean expected, String pagesUpdate) {
        assertEquals(expected, printQueueValidator.isValid(PrintQueueValidator.parseSequence(pagesUpdate)));
    }

    @ParameterizedTest
    @MethodSource("correctOrderingData")
    void shouldCorrectOrdering(String expected, String value) {
        var parsedExpected = PrintQueueValidator.parseSequence(expected);
        var parsedValue = PrintQueueValidator.parseSequence(value);
        assertEquals(parsedExpected, printQueueValidator.correctOrdering(parsedValue));
    }
}