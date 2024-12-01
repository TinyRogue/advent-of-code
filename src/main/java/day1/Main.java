package day1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private final static String puzzleInputPath = "day1/puzzle_input.txt";

    public static void main(String[] args) {
        var aMeasurements = new ArrayList<Integer>();
        var bMeasurements = new ArrayList<Integer>();
        var classLoader = Main.class.getClassLoader();
        try (var is = classLoader.getResourceAsStream(puzzleInputPath)) {
            if (is == null) {
                throw new FileNotFoundException("No puzzle input found!");
            }
            for (var line : new String(is.readAllBytes()).split("\n")) {
                var parts = line.split(" ");
                aMeasurements.add(Integer.parseInt(parts[0]));
                bMeasurements.add(Integer.parseInt(parts[parts.length - 1]));
            }
        } catch (IOException e) {
            System.err.println("Error reading puzzle input file!");
            return;
        }
        var result = DistanceMeasure.totalDistanceBetween(aMeasurements, bMeasurements);
        System.out.println(result);
    }
}
