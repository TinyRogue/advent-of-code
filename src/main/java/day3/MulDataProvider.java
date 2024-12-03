package day3;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class MulDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day3/puzzle_input.txt";
    private static final String SAMPLE_DATA_INPUT_PATH = "day3/test_data.txt";
    private String memoryData = null;

    public String memoryData() {
        return memoryData;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        memoryData = DataLoader.loadData(resourcePath);
    }
}
