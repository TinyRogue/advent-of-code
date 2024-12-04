package day4;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class GridDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day4/puzzle_input.txt";
    private static final String SAMPLE_DATA_PART_1_INPUT_PATH = "day4/test_data_part_1.txt";
    private static final String SAMPLE_DATA_PART_2_INPUT_PATH = "day4/test_data_part_2.txt";
    private static final String soughtWord = "XMAS";
    private static final String soughtXWord = "MAS";
    private static final int wordCountResult = 18;
    private static final int wordCountXResult = 9;
    private String[] grid;

    public String[] getGrid() {
        return grid;
    }

    public int getSampleWordCountResult() {
        return wordCountResult;
    }

    public int getSampleWordCountXResult() {
        return wordCountXResult;
    }

    public String getSoughtWord() {
        return soughtWord;
    }

    public String getSoughtXWord() {
        return soughtXWord;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = switch (type) {
            case DataType.SAMPLE_PART_1 -> SAMPLE_DATA_PART_1_INPUT_PATH;
            case DataType.SAMPLE_PART_2 -> SAMPLE_DATA_PART_2_INPUT_PATH;
            case DataType.PUZZLE_INPUT -> PUZZLE_INPUT_PATH;
        };
        String raw = DataLoader.loadData(resourcePath);
        grid = raw.split("\n");
    }
}
