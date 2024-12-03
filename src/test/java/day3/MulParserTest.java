package day3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MulParserTest {
    private static final MulDataProvider dataProvider = new MulDataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(DataType.SAMPLE);
    }

    @Test
    void parseWithSampleDataTest() {
        assertEquals(161, MulParser.parse(dataProvider.memoryData()));
    }
}