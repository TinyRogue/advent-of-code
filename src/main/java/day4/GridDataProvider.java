package day4;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class GridDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day4/puzzle_input.txt";
    private static final String SAMPLE_DATA_INPUT_PATH = "day4/test_data.txt";
    private static final String soughtWord = "XMAS";
    private static final int wordCountResult = 18;
    private String[] grid;

    public String[] getGrid() {
        return grid;
    }

    public int getSampleWordCountResult() {
        return wordCountResult;
    }

    public String getSoughtWord() {
        return soughtWord;
    }

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        String raw = DataLoader.loadData(resourcePath);
        grid = raw.split("\n");
    }
}
