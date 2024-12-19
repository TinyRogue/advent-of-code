package com.github.tinyrogue.day6;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuardMovementPredictorTest {
    private static final String[] map = ResourceLoader.ofDay(6).getTestPart1().split("\n");
    private static final int EXPECTED_TRAVELLED_PATH_LENGTH = 41;
    private static final int EXPECTED_LOOPING_OBSTRUCTION_COUNT = 6;


    private static Stream<Arguments> simpleMapTest() {
        return Stream.of(
                Arguments.of(2, new String[]{
                        ".",
                        "^",
                }),
                Arguments.of(7, new String[]{
                        "#.###",
                        "#...#",
                        "#.^.#",
                        "#####",
                })
        );
    }

    @ParameterizedTest
    @MethodSource("simpleMapTest")
    void pathTravelledLengthBasicTest(long expected, String[] map) {
        assertEquals(expected, GuardMovementPredictor.pathTravelledLength(map));
    }

    @Test
    void pathTravelledLengthTest() {
        assertEquals(EXPECTED_TRAVELLED_PATH_LENGTH, GuardMovementPredictor.pathTravelledLength(map));
    }

    @Test
    void countLoopingObstructionsTest() {
        assertEquals(EXPECTED_LOOPING_OBSTRUCTION_COUNT, GuardMovementPredictor.countLoopingObstructions(map));
    }
}