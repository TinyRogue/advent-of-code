package day2;

import utils.DataLoader;
import utils.DataProvider;
import utils.DataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportsDataProvider implements DataProvider {
    private static final String PUZZLE_INPUT_PATH = "day2/puzzle_input.txt";
    private static final String SAMPLE_DATA_INPUT_PATH = "day2/test_data.txt";
    private final List<List<Integer>> reports = new ArrayList<>();

    public List<List<Integer>> getReports() {
        return reports;
    }

    public void loadData(DataType type) throws IOException {
        var resourcePath = type == DataType.PUZZLE_INPUT ? PUZZLE_INPUT_PATH : SAMPLE_DATA_INPUT_PATH;
        var data = DataLoader.loadData(resourcePath);
        for (var reportData : data.split("\n")) {
            var scanner = new Scanner(reportData);
            var report = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                report.add(scanner.nextInt());
            }
            reports.add(report);
        }
    }

    public static final class NonTolerant {
        public static List<Boolean> getSampleDataRespectiveAnswers() {
            return List.of(true, false, false, false, false, true);
        }

        public static int getSampleDataTotalResults() {
            return 2;
        }
    }

    public static final class Tolerant {
        public static List<Boolean> getSampleDataRespectiveAnswers() {
            return List.of(true, false, false, true, true, true);
        }

        public static int getSampleDataTotalResults() {
            return 4;
        }
    }
}
