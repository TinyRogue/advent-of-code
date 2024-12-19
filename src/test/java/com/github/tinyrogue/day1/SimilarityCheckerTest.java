package com.github.tinyrogue.day1;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityCheckerTest {
    private static final DataParser dataParser = new DataParser(ResourceLoader.ofDay(1).getTestPart1());
    private static final int EXPECTED_SIMILARITY_SCORE = 31;

    @Test
    void shouldCalculateSimilarityScore() {
        assertEquals(
                EXPECTED_SIMILARITY_SCORE,
                SimilarityChecker.score(
                        dataParser.getFirstLocationIds(),
                        dataParser.getSecondLocationIds()
                )
        );
    }

    @Test
    void shouldReturnZeroForEmptyLists() {
        assertEquals(
                0,
                SimilarityChecker.score(List.of(), List.of())
        );
    }

    @Test
    void shouldReturnZeroForNonOverlappingLists() {
        assertEquals(
                0,
                SimilarityChecker.score(List.of(1, 2, 3), List.of(4, 5, 6))
        );
    }
}
