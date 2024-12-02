package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityCheckerTest {
    private static final DataProvider dataProvider = new DataProvider();
    private static final String testDataFilepath = "day1/test_data.txt";

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(testDataFilepath);
    }

    @Test
    void score() {
        assertEquals(
                dataProvider.getTestExpectedSimilarityScore(),
                SimilarityChecker.score(
                        dataProvider.getALocationIds(),
                        dataProvider.getBLocationIds()
                )
        );
    }
}
