package day8;

import utils.DataType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var mapDataProvider = new MapDataProvider();
        try {
            mapDataProvider.loadData(DataType.PUZZLE_INPUT);
        } catch (IOException _) {
            System.err.println("Could not load puzzle input data");
            System.exit(1);
        }

        var antinodes = AntinodeMeter.signalImpact(mapDataProvider.getMap(), false);
        var harmonicAntinodes = AntinodeMeter.signalImpact(mapDataProvider.getMap(), true);
        System.out.println("Number of antinodes is " + antinodes + ", including harmonics it increases to " + harmonicAntinodes + ".");
    }
}
