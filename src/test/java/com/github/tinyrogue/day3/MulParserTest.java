package com.github.tinyrogue.day3;

import com.github.tinyrogue.data.ResourceLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MulParserTest {
    private final ResourceLoader resourceLoader = ResourceLoader.ofDay(3);

    @Test
    void parseWithSampleDataTest() {
        assertEquals(161L, MulParser.parse(resourceLoader.getTestPart1()));
    }

    @Test
    void instructedParseWithSampleDataTest() {
        assertEquals(48L, MulParser.instructedParse(resourceLoader.getTestPart2()));
    }
}
