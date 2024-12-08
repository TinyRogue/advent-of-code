package day5;

import utils.DataType;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var pagesDataProvider = new PagesDataProvider();
        try {
            pagesDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException _) {
            System.err.println("Could not load data from file");
            System.exit(1);
        }

        var printQueueValidator = new PrintQueueValidator(pagesDataProvider.getRules());
        var part1Res = Arrays.stream(pagesDataProvider.getUpdates())
                .map(PrintQueueValidator::parseSequence)
                .filter(printQueueValidator::isValid)
                .reduce(0L, (subtotal, updateSequence) -> subtotal + updateSequence.get(updateSequence.size() / 2), Long::sum);
        System.out.println("The sum of the valid update sequence middle elements is " + part1Res);

        var part2Res = Arrays.stream(pagesDataProvider.getUpdates())
                .map(PrintQueueValidator::parseSequence)
                .filter(sequence -> !printQueueValidator.isValid(sequence))
                .map(printQueueValidator::correctOrdering)
                .reduce(0L, (subtotal, updateSequence) -> subtotal + updateSequence.get(updateSequence.size() / 2), Long::sum);
        System.out.println("The sum of the corrected update sequence middle elements is " + part2Res);
    }
}
