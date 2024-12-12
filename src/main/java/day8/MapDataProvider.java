package day8;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class MapDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day8/puzzle_input.txt";
    private static final String SAMPLE_DATA_INPUT_PATH = "day8/test_data.txt";
    private static final short SAMPLE_RESULT = 14;
    private static final short HARMONIC_SAMPLE_RESULT = 34;
    private String[] map;

    public short getSampleResult() {
        return SAMPLE_RESULT;
    }

    public short getHarmonicSampleResult() {
        return HARMONIC_SAMPLE_RESULT;
    }

    public String[] getMap() {
        return map;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        map = DataLoader.loadData(resourcePath).split("\n");
    }
}
