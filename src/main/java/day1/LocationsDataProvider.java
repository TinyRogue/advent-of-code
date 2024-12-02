package day1;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationsDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day1/puzzle_input.txt";
    private static final String SAMPLE_DATA_INPUT_PATH = "day1/test_data.txt";
    private final List<Integer> aLocationIds = new ArrayList<>();
    private final List<Integer> bLocationIds = new ArrayList<>();

    public List<Integer> getALocationIds() {
        return aLocationIds;
    }

    public List<Integer> getBLocationIds() {
        return bLocationIds;
    }

    public int getTestExpectedDistance() {
        return 11;
    }

    public int getTestExpectedSimilarityScore() {
        return 31;
    }

    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        var testData = DataLoader.loadData(resourcePath);
        var lines = testData.split("\n");
        for (var line : lines) {
            var parts = line.split(" ");
            aLocationIds.add(Integer.parseInt(parts[0]));
            bLocationIds.add(Integer.parseInt(parts[parts.length - 1]));
        }
    }
}


