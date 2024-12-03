package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMeasureTest {
    private static final LocationsDataProvider LOCATIONS_DATA_PROVIDER = new LocationsDataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        LOCATIONS_DATA_PROVIDER.loadData(DataType.SAMPLE_PART_1);
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
                LOCATIONS_DATA_PROVIDER.getTestExpectedDistance(),
                DistanceMeasure.totalDistanceBetween(
                        LOCATIONS_DATA_PROVIDER.getALocationIds(),
                        LOCATIONS_DATA_PROVIDER.getBLocationIds()
                )
        );
    }
}
