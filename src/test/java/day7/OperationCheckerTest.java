package day7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.DataType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationCheckerTest {
    private static final OperationsDataProvider dataProvider = new OperationsDataProvider();

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(DataType.SAMPLE_PART_1);
    }

    public static Stream<Arguments> getSampleData() {
        return Stream.of(
                Arguments.of(true, Map.entry(190L, List.of(10L, 19L))),
                Arguments.of(true, Map.entry(3267L, List.of(81L, 40L, 27L))),
                Arguments.of(false, Map.entry(156L, List.of(15L, 6L))),
                Arguments.of(false, Map.entry(7290L, List.of(6L, 8L, 6L, 15L))),
                Arguments.of(false, Map.entry(161011L, List.of(16L, 10L, 13L))),
                Arguments.of(false, Map.entry(192L, List.of(17L, 8L, 14L))),
                Arguments.of(false, Map.entry(21037L, List.of(9L, 7L, 18L, 13L))),
                Arguments.of(true, Map.entry(292L, List.of(11L, 6L, 16L, 20L)))
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleData")
    void isFeasible(boolean expected, Map.Entry<Long, List<Long>> operations) {
        assertEquals(expected, OperationChecker.isFeasible(operations.getKey(), operations.getValue()));
    }

    @Test
    void calibration() {
        assertEquals(dataProvider.getSampleResult(), OperationChecker.calibrationValue(dataProvider.getOperations()));
    }
}