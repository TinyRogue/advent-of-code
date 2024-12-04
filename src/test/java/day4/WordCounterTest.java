package day4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterTest {
    private static final GridDataProvider gridDataProvider = new GridDataProvider();
    private static final String edgeTestGrid = """
            S.........S
            A.X...X..AA
            M..M.M..M.M
            X...A..X..X
            XMAS.S.XMAS
            SAMX...SAMX
            X.SXM.SX..X
            M.MA.A..M.M
            AA..M.S..AA
            S..X.X....S
            """;
    private static final long edgeTestResult = 16;

    @BeforeAll
    static void setUp() throws IOException {
        gridDataProvider.loadData(DataType.SAMPLE_PART_1);
    }

    @Test
    void countTest() {
        assertEquals(
                edgeTestResult,
                WordCounter.count("XMAS", edgeTestGrid.split("\n"))
        );
        assertEquals(
                gridDataProvider.getSampleWordCountResult(),
                WordCounter.count(gridDataProvider.getSoughtWord(), gridDataProvider.getGrid())
        );
    }
}
