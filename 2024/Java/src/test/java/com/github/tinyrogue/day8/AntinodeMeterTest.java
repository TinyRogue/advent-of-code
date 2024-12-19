package com.github.tinyrogue.day8;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AntinodeMeterTest {
    private static final String[] TEST_MAP = ResourceLoader.ofDay(8).getTestPart1().split("\n");
    private static final int EXPECTED_IMPACT_NO_HARMONICS = 14;
    private static final int EXPECTED_IMPACT_WITH_HARMONICS = 34;

    @Test
    void shouldCalculateSignalImpactWithoutHarmonics() {
        assertEquals(EXPECTED_IMPACT_NO_HARMONICS, AntinodeMeter.signalImpact(TEST_MAP, false));
    }

    @Test
    void shouldCalculateSignalImpactWithHarmonics() {
        assertEquals(EXPECTED_IMPACT_WITH_HARMONICS, AntinodeMeter.signalImpact(TEST_MAP, true));
    }
}
