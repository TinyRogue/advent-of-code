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

class NonTolerantReportAnalyserTest {
    private static final DataParser dataParser = new DataParser(ResourceLoader.ofDay(2).getTestPart1());
    private final static ReportAnalyser reportAnalyser = new NonTolerantReportAnalyser();
    public static List<Boolean> answers = List.of(true, false, false, false, false, true);

    private static Stream<Arguments> getSampleData() {
        return IntStream.range(0, dataParser.getReports().size()).mapToObj(i -> Arguments.of(answers.get(i), dataParser.getReports().get(i)));
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isSafeTest(boolean expected, List<Integer> data) {
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
