package com.github.tinyrogue.day2;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TolerantReportAnalyserTest {
    private static final DataParser dataParser = new DataParser(ResourceLoader.ofDay(2).getTestPart1());
    private final static ReportAnalyser reportAnalyser = new TolerantReportAnalyser();
    public static List<Boolean> answers = List.of(true, false, false, true, true, true);

    private static Stream<Arguments> getSampleData() {
        return IntStream.range(0, dataParser.getReports().size()).mapToObj(i -> Arguments.of(answers.get(i), dataParser.getReports().get(i)));
    }

    private static Stream<Arguments> edgeCasesData() {
        return Stream.of(
                Arguments.of(true, List.of()),                      // size
                Arguments.of(true, List.of(1)),                 // size
                Arguments.of(true, List.of(0, 1)),                  // increasing
                Arguments.of(true, List.of(1, 2, 3, 4, 5)),         // increasing
                Arguments.of(true, List.of(1, 4, 7, 10, 13)),       // increasing with skipping
                Arguments.of(true, List.of(5, 4)),                  // decreasing
                Arguments.of(true, List.of(5, 4, 3, 2, 1)),         // decreasing
                Arguments.of(true, List.of(5, 1, -2, -3, -5)),      // decreasing with skipping

                Arguments.of(true, List.of(5, 3, 1, 0, -3)),        // base, one offs below
                Arguments.of(true, List.of(1024, 3, 1, 0, -3)),     // first invalid
                Arguments.of(true, List.of(5, 3, 1, 0, 1024)),      // last invalid
                Arguments.of(true, List.of(5, 3, 1024, 0, -3)),     // middle invalid
                Arguments.of(true, List.of(1, 1)),                  // same one off
                Arguments.of(true, List.of(1, 2, 1, 0, -1)),        // first invalid, decreasing one off
                Arguments.of(true, List.of(1, 0, 1, 2, 3)),         // first invalid, increasing one off
                Arguments.of(true, List.of(1, 2, 3, 4, 3)),         // last invalid, increasing one off
                Arguments.of(true, List.of(4, 3, 2, 1, 2)),         // last invalid, decreasing one off
                Arguments.of(true, List.of(1, 4, 3, 2)),            // first invalid, decreasing one off
                Arguments.of(true, List.of(2, 5, 3, 1)),            // first invalid, decreasing one off
                Arguments.of(true, List.of(1, 1, 2, 3, 4)),         // same, one invalid, increasing
                Arguments.of(true, List.of(1, 1, -2, -3, -4)),      // same, one invalid, decreasing
                Arguments.of(true, List.of(9, 11, 9)),              // one of, increasing, decreasing


                Arguments.of(false, List.of(9, 11, 16, 9)),         // double error, increasing, decreasing, same element
                Arguments.of(false, List.of(1, 1, 1)),              // same double error
                Arguments.of(false, List.of(1024, 2, 3, 4, 1024)),  // double error on two sides
                Arguments.of(false, List.of(1, 2, 3, 1024, 1025)),  // double error on right side
                Arguments.of(false, List.of(1024, 1025, 3, 4, 5))   // double error on the left side
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isSafeTest(boolean expected, List<Integer> data) {
        assertEquals(expected, reportAnalyser.isSafe(data));
    }

    @ParameterizedTest
    @MethodSource("edgeCasesData")
    void isSafeEdgeCasesTest(boolean expected, List<Integer> data) {
        assertEquals(expected, reportAnalyser.isSafe(data));
    }

    @Test
    void getSafeReportNumberTest() {
        assertEquals(
                answers.stream().filter(Boolean::booleanValue).count(),
                reportAnalyser.getSafeReportsNumber(dataParser.getReports())
        );
    }
}
