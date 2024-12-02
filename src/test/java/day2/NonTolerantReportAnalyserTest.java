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

class NonTolerantReportAnalyserTest {
    private final static ReportsDataProvider dataProvider = new ReportsDataProvider();
    private final static ReportAnalyser reportAnalyser = new NonTolerantReportAnalyser();

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(DataType.SAMPLE);
    }

    private static Stream<Arguments> getSampleData() {
        var reports = dataProvider.getReports();
        var expected = ReportsDataProvider.NonTolerant.getSampleDataRespectiveAnswers();
        return IntStream.range(0, reports.size()).mapToObj(i -> Arguments.of(expected.get(i), reports.get(i)));
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isSafeTest(boolean expected, List<Integer> data) {
        assertEquals(expected, reportAnalyser.isSafe(data));
    }

    @Test
    void getSafeReportNumberTest() {
        assertEquals(
                ReportsDataProvider.NonTolerant.getSampleDataTotalResults(),
                reportAnalyser.getSafeReportsNumber(dataProvider.getReports())
        );
    }
}
