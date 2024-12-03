package day3;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class MulDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day3/puzzle_input.txt";
    private static final String SAMPLE_DATA_PART_1_INPUT_PATH = "day3/test_data_part_1.txt";
    private static final String SAMPLE_DATA_PART_2_INPUT_PATH = "day3/test_data_part_2.txt";
    private String memoryData = null;

    public String memoryData() {
        return memoryData;
    }

    public Long getParseSampleResult() {
        return 161L;
    }

    public Long getInstructedParseSampleResult() {
        return 48L;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = switch (type) {
            case DataType.SAMPLE_PART_1 -> SAMPLE_DATA_PART_1_INPUT_PATH;
            case DataType.SAMPLE_PART_2 -> SAMPLE_DATA_PART_2_INPUT_PATH;
            case DataType.PUZZLE_INPUT -> PUZZLE_INPUT_PATH;
        };
        memoryData = DataLoader.loadData(resourcePath);
    }
}
