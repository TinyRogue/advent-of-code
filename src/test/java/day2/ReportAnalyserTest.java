package day2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.DataType;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportAnalyserTest {
    private final static ReportsDataProvider dataProvider = new ReportsDataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(DataType.SAMPLE);
    }

    private static Stream<Arguments> getSampleData() {
        var reports = dataProvider.getReports();
        var expected = dataProvider.getSampleDataRespectiveAnswers();
        return IntStream.range(0, reports.size()).mapToObj(i -> Arguments.of(expected.get(i), reports.get(i)));
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isSafeTest(boolean expected, List<Integer> data) {
        assertEquals(expected, ReportAnalyser.isSafe(data));
    }

    @Test
    void getSafeReportNumberTest() {
        assertEquals(
                dataProvider.getSampleDataTotalResults(),
                ReportAnalyser.getSafeReportsNumber(dataProvider.getReports())
        );
    }
}
