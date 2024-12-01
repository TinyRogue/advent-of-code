package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceMeasureTest {
    private static final String testDataFilepath = "day1/test_data.txt";
    private static final int testDataAnswer = 11;
    private static final List<Integer> testDataAMeasurements = new ArrayList<>();
    private static final List<Integer> testDataBMeasurements = new ArrayList<>();

    @BeforeAll
    static void setUp() throws IOException {
        var classLoader = DistanceMeasureTest.class.getClassLoader();
        var is = classLoader.getResourceAsStream(testDataFilepath);
        if (is == null) {
            throw new FileNotFoundException("No test data found!");
        }
        String testData = new String(is.readAllBytes());
        is.close();

        var lines = testData.split("\n");
        for (var line : lines) {
            var parts = line.split(" ");
            testDataAMeasurements.add(Integer.parseInt(parts[0]));
            testDataBMeasurements.add(Integer.parseInt(parts[parts.length - 1]));
        }
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
        assertEquals(testDataAnswer, DistanceMeasure.totalDistanceBetween(testDataAMeasurements, testDataBMeasurements));
    }
}


