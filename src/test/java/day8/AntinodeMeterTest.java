package day8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AntinodeMeterTest {
    private static final MapDataProvider mapDataProvider = new MapDataProvider();


    @BeforeAll
    static void setUp() throws IOException {
        mapDataProvider.loadData(DataType.SAMPLE_PART_1);
    }

    @Test
    void signalImpact() {
        assertEquals(mapDataProvider.getSampleResult(), AntinodeMeter.signalImpact(mapDataProvider.getMap(), false));
    }

    @Test
    void harmonicSignalImpact() {
        assertEquals(mapDataProvider.getHarmonicSampleResult(), AntinodeMeter.signalImpact(mapDataProvider.getMap(), true));
    }
}
