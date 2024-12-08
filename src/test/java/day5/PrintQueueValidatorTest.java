package day5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.DataType;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintQueueValidatorTest {
    private static final PagesDataProvider dataProvider = new PagesDataProvider();
    private static PrintQueueValidator printQueueValidator;

    @BeforeAll
    static void setUp() throws IOException {
        dataProvider.loadData(DataType.SAMPLE_PART_1);
        printQueueValidator = new PrintQueueValidator(dataProvider.getRules());
    }

    private static Stream<Arguments> sampleTestData() {
        return Stream.of(
                Arguments.of(true, "75,47,61,53,29"),
                Arguments.of(true, "97,61,53,29,13"),
                Arguments.of(true, "75,29,13"),
                Arguments.of(false, "75,97,47,61,53"),
                Arguments.of(false, "61,13,29"),
                Arguments.of(false, "97,13,75,29,47")
        );
    }

    public static Stream<Arguments> correctOrderingData() {
        return Stream.of(
                Arguments.of("97,75,47,61,53", "75,97,47,61,53"),
                Arguments.of("61,29,13", "61,13,29"),
                Arguments.of("97,75,47,29,13", "97,13,75,29,47")
        );
    }

    @ParameterizedTest
    @MethodSource("sampleTestData")
    void isValid(boolean expected, String pagesUpdate) {
        assertEquals(expected, printQueueValidator.isValid(PrintQueueValidator.parseSequence(pagesUpdate)));
    }

    @ParameterizedTest
    @MethodSource("correctOrderingData")
    void correctOrderingTest(String expected, String value) {
        var parsedExpected = PrintQueueValidator.parseSequence(expected);
        var parsedValue = PrintQueueValidator.parseSequence(value);
        assertEquals(parsedExpected, printQueueValidator.correctOrdering(parsedValue));
    }
}