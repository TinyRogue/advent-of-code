package day3;

import org.junit.jupiter.api.Test;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MulParserTest {
    private static final MulDataProvider dataProvider = new MulDataProvider();

    @Test
    void parseWithSampleDataTest() throws IOException {
        dataProvider.loadData(DataType.SAMPLE_PART_1);
        assertEquals(dataProvider.getParseSampleResult(), MulParser.parse(dataProvider.memoryData()));
    }

    @Test
    void instructedParseWithSampleDataTest() throws IOException {
        dataProvider.loadData(DataType.SAMPLE_PART_2);
        assertEquals(dataProvider.getInstructedParseSampleResult(), MulParser.instructedParse(dataProvider.memoryData()));
    }
}
