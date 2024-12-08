package day5;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;

public class PagesDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day5/puzzle_input.txt";
    private static final String SAMPLE_DATA_PART_1_INPUT_PATH = "day5/test_data.txt";
    private String[] rules;
    private String[] updates;

    @Override
    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_PART_1_INPUT_PATH;
        var rawData = DataLoader.loadData(resourcePath);
        var data = rawData.split("\n\n");
        rules = data[0].split("\n");
        updates = data[1].split("\n");
    }

    public String[] getRules() {
        return rules;
    }

    public String[] getUpdates() {
        return updates;
    }
}
