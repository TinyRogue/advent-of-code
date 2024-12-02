package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMeasureTest {
    private final static DataProvider dataProvider = new DataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 1",
            "2, 1, 1",
            "2, 2, 0",
    })
    void measureDistanceBetweenTwoPointsTest(int measureA, int measureB, int expected) {
        assertEquals(expected, DistanceMeasure.distanceBetween(measureA, measureB));
    }

    @Test
    void totalDistance() {
        assertEquals(
                dataProvider.getTestDataAnswer(),
                DistanceMeasure.totalDistanceBetween(
                        dataProvider.getTestDataAMeasurements(),
                        dataProvider.getTestDataBMeasurements()
                )
        );
    }
}
