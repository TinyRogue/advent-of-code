package day6;

import utils.DataType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MapDataProvider mapDataProvider = new MapDataProvider();
        try {
            mapDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException _) {
            System.err.println("Could not load puzzle input.");
        }

        var result = GuardMovementPredictor.pathTravelledLength(mapDataProvider.getMap());
        System.out.println("Unique travelled points number is: " + result);
    }
}
