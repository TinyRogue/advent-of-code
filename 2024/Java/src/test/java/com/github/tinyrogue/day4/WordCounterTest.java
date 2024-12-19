package com.github.tinyrogue.day4;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterTest {
    private static final String EDGE_TEST_GRID = """
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
    private static final long EDGE_TEST_RESULT = 16;
    private static final String EDGE_X_TEST_GRID = """
            M.M....S.M
            .A......A.
            S.S....S.M
            ..........
            ..........
            ..........
            ..........
            ..........
            M.S....S.S
            .A......A.
            M.S....M.M
            """;
    private static final long EDGE_X_RESULT = 4;
    private static final String[] GRID = ResourceLoader.ofDay(4).getTestPart1().split("\n");
    private static final long GRID_TEST_RESULT = 18;
    private static final long GRID_TEST_X_RESULT = 9;

    @Test
    void countTest() {
        assertEquals(EDGE_TEST_RESULT, WordCounter.count("XMAS", EDGE_TEST_GRID.split("\n")));
        assertEquals(GRID_TEST_RESULT, WordCounter.count("XMAS", GRID));
    }

    @Test
    void countXTest() {
        assertEquals(EDGE_X_RESULT, WordCounter.countXShaped("MAS", EDGE_X_TEST_GRID.split("\n")));
        assertEquals(GRID_TEST_X_RESULT, WordCounter.countXShaped("MAS", GRID));
    }
}
