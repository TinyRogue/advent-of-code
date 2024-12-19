package com.github.tinyrogue.day1;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMeasureTest {
    private static final DataParser dataParser = new DataParser(ResourceLoader.ofDay(1).getTestPart1());
    private static final int EXPECTED_TOTAL_DISTANCE = 11;

    @ParameterizedTest
    @CsvSource({
            "1, 2, 1",
            "2, 1, 1",
            "2, 2, 0",
    })
    void shouldCorrectlyMeasureDistanceBetweenPoints(int a, int b, int expected) {
        assertEquals(expected, DistanceMeasure.distanceBetween(a, b));
    }

    @Test
    void shouldHandleEmptyLocationLists() {
        assertEquals(0, DistanceMeasure.totalDistanceBetween(List.of(), List.of()));
    }

    @Test
    void shouldCalculateTotalDistance() {
        assertEquals(EXPECTED_TOTAL_DISTANCE,
                DistanceMeasure.totalDistanceBetween(dataParser.getFirstLocationIds(), dataParser.getSecondLocationIds())
        );
    }
}
