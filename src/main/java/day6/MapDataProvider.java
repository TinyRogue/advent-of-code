package day6;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class MapDataProvider implements DataProvider {
    public static final long SAMPLE_DATA_PART_1_RESULT = 41L;
    public static final int SAMPLE_DATA_PART_2_RESULT = 6;
    private static final String PUZZLE_INPUT_PATH = "day6/puzzle_input.txt";
    private static final String SAMPLE_DATA_PART_1_INPUT_PATH = "day6/test_data.txt";
    private String[] map;

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_PART_1_INPUT_PATH;
        map = DataLoader.loadData(resourcePath).split("\n");
    }

    public String[] getMap() {
        return map;
    }
}
