package day3;

import utils.DataType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MulDataProvider mulDataProvider = new MulDataProvider();
        try {
            mulDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException _) {
            System.out.println("No puzzle input");
        }
        var result = MulParser.parse(mulDataProvider.memoryData());
        var instructedResult = MulParser.instructedParse(mulDataProvider.memoryData());
        System.out.printf("Multiplication result is %d and instructed result is %d\n", result, instructedResult);
    }
}
