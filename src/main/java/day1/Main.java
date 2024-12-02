package day1;

import java.io.IOException;
import java.text.MessageFormat;

public class Main {
    private final static String puzzleInputPath = "day1/puzzle_input.txt";

    public static void main(String[] args) {
        var dataProvider = new DataProvider();
        try {
            dataProvider.loadData(puzzleInputPath);
        } catch (IOException e) {
            System.err.println("Puzzle input could not be loaded.");
            return;
        }

        var distance = DistanceMeasure.totalDistanceBetween(
                dataProvider.getALocationIds(),
                dataProvider.getBLocationIds()
        );

        var similarity = SimilarityChecker.score(
                dataProvider.getALocationIds(),
                dataProvider.getBLocationIds()
        );

        System.out.println(
                MessageFormat.format("Distance: {0}\nSimilarity: {1}", distance, similarity)
        );
    }
}
