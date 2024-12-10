package day7;

import utils.DataType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        OperationsDataProvider operationsDataProvider = new OperationsDataProvider();
        try {
            operationsDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException e) {
            System.err.println("Could not load puzzle input.");
            System.exit(1);
        }
        var calibrationValue = OperationChecker.calibrationValue(operationsDataProvider.getOperations());
        System.out.println("Calibration value is: " + calibrationValue);
    }
}
