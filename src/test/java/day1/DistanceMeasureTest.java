package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMeasureTest {
    private static final DataProvider dataProvider = new DataProvider();
    private static final String testDataFilepath = "day1/test_data.txt";

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(testDataFilepath);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 1",
            "2, 1, 1",
            "2, 2, 0",
    })
    void measureDistanceBetweenTwoPointsTest(int a, int b, int expected) {
        assertEquals(expected, DistanceMeasure.distanceBetween(a, b));
    }

    @Test
    void totalDistance() {
        assertEquals(
                dataProvider.getTestExpectedDistance(),
                DistanceMeasure.totalDistanceBetween(
                        dataProvider.getALocationIds(),
                        dataProvider.getBLocationIds()
                )
        );
    }
}