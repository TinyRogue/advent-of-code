package day1;

import utils.DataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
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

    public void loadData(String resourcePath) throws IOException {
        var testData = DataLoader.loadData(resourcePath);
        var lines = testData.split("\n");
        for (var line : lines) {
            var parts = line.split(" ");
            aLocationIds.add(Integer.parseInt(parts[0]));
            bLocationIds.add(Integer.parseInt(parts[parts.length - 1]));
        }
    }
}


