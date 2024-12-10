package day7;

import day7.operators.Add;
import day7.operators.Concat;
import day7.operators.Multiple;
import utils.DataType;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationsDataProvider operationsDataProvider = new OperationsDataProvider();
        try {
            operationsDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException e) {
            System.err.println("Could not load puzzle input.");
            System.exit(1);
        }
        var calibrationValue = OperationChecker.calibrationValue(operationsDataProvider.getOperations(), List.of(new Add(), new Multiple()));
        System.out.println("Calibration value is: " + calibrationValue);
        var calibrationValueWithConcat = OperationChecker.calibrationValue(operationsDataProvider.getOperations(), List.of(new Add(), new Multiple(), new Concat()));
        System.out.println("Calibration value with concatenation is: " + calibrationValueWithConcat);
    }
}
