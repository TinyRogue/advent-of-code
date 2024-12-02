package day2;

import utils.DataType;

import java.io.IOException;
import java.text.MessageFormat;

public class Main {
    private static final ReportsDataProvider dataProvider = new ReportsDataProvider();

    public static void main(String[] args) {
        try {
            dataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException _) {
            System.err.println("Could not load puzzle input.");
        }
        var result = ReportAnalyser.getSafeReportsNumber(dataProvider.getReports());
        System.out.println(MessageFormat.format("The number of safe reports is {0}.", result));
    }
}
// 387 too high
// 346 too high
