package day1;

import utils.DataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private static final String testDataFilepath = "day1/test_data.txt";
    private final List<Integer> testDataAMeasurements = new ArrayList<>();
    private final List<Integer> testDataBMeasurements = new ArrayList<>();

    public List<Integer> getTestDataBMeasurements() {
        return testDataBMeasurements;
    }

    public List<Integer> getTestDataAMeasurements() {
        return testDataAMeasurements;
    }

    public int getTestDataAnswer() {
        return 11;
    }

    public void loadData() throws IOException {
        var testData = DataLoader.loadData(testDataFilepath);
        var lines = testData.split("\n");
        for (var line : lines) {
            var parts = line.split(" ");
            testDataAMeasurements.add(Integer.parseInt(parts[0]));
            testDataBMeasurements.add(Integer.parseInt(parts[parts.length - 1]));
        }
    }
}


