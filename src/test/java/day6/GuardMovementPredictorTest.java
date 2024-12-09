package day6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.DataType;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuardMovementPredictorTest {
    private static final MapDataProvider mapDataProvider = new MapDataProvider();


    @BeforeAll
    static void setUp() throws IOException {
        mapDataProvider.loadData(DataType.SAMPLE_PART_1);
    }

    private static Stream<Arguments> simpleMapTest() {
        return Stream.of(
                Arguments.of(2, new String[]{
                        ".",
                        "^",
                }),
                Arguments.of(7, new String[]{
                        "#.###",
                        "#...#",
                        "#.^.#",
                        "#####",
                })
        );
    }

    @ParameterizedTest
    @MethodSource("simpleMapTest")
    void pathTravelledLengthBasicTest(long expected, String[] map) {
        assertEquals(expected, GuardMovementPredictor.pathTravelledLength(map));
    }

    @Test
    void pathTravelledLengthTest() {
        assertEquals(MapDataProvider.SAMPLE_DATA_RESULT, GuardMovementPredictor.pathTravelledLength(mapDataProvider.getMap()));
    }
}