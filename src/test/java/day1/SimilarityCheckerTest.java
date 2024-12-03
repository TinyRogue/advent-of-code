package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityCheckerTest {
    private static final LocationsDataProvider LOCATIONS_DATA_PROVIDER = new LocationsDataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        LOCATIONS_DATA_PROVIDER.loadData(DataType.SAMPLE_PART_1);
    }

    @Test
    void score() {
        assertEquals(
                LOCATIONS_DATA_PROVIDER.getTestExpectedSimilarityScore(),
                SimilarityChecker.score(
                        LOCATIONS_DATA_PROVIDER.getALocationIds(),
                        LOCATIONS_DATA_PROVIDER.getBLocationIds()
                )
        );
    }
}
