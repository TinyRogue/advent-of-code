package com.github.tinyrogue.day8;

import java.util.*;
import java.util.stream.Collectors;

public class AntinodeMeter {

    public static long signalImpact(final String[] map, final boolean includeHarmonics) {
        var mapHeight = map.length;
        var mapWidth = map[0].length();
        var antennas = getAntennas(map);
        if (includeHarmonics) {
            return getHarmonicAntinodePositions(antennas, mapHeight, mapWidth).size();
        }
        return getAntinodePositions(antennas, mapHeight, mapWidth).size();
    }

    private static boolean antinodeWithingArea(final Position antinode, final int maxHeight, final int maxWidth) {
        return antinode.x() >= 0 && antinode.x() < maxWidth && antinode.y() >= 0 && antinode.y() < maxHeight;
    }

    private static Set<Position> getHarmonicAntinodePositions(final Map<Character, List<Position>> antennas, final int maxHeight, final int maxWidth) {
        Set<Position> antinodes = new HashSet<>();
        for (var antenna : antennas.entrySet()) {
            var positions = antenna.getValue();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j) continue;
                    var a1 = positions.get(i);
                    var a2 = positions.get(j);
                    var vDistance = a1.y() - a2.y();
                    var hDistance = a1.x() - a2.x();
                    var antinode = a1.offset(hDistance, vDistance);
                    antinodes.add(a1);
                    while (antinodeWithingArea(antinode, maxHeight, maxWidth)) {
                        antinodes.add(antinode);
                        antinode = antinode.offset(hDistance, vDistance);
                    }
                }
            }
        }

        return antinodes;
    }

    private static Set<Position> getAntinodePositions(final Map<Character, List<Position>> antennas, final int maxHeight, final int maxWidth) {
        Set<Position> antinodes = new HashSet<>();
        for (var antenna : antennas.entrySet()) {
            var positions = antenna.getValue();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j) continue;
                    var a1 = positions.get(i);
                    var a2 = positions.get(j);
                    var vDistance = a1.y() - a2.y();
                    var hDistance = a1.x() - a2.x();
                    var antinode = a1.offset(hDistance, vDistance);
                    if (!antinodeWithingArea(antinode, maxHeight, maxWidth)) {
                        continue;
                    }
                    antinodes.add(antinode);
                }
            }
        }
        return antinodes;
    }

    private static Map<Character, List<Position>> getAntennas(final String[] map) {
        Map<Character, List<Position>> antennas = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length(); j++) {
                var c = map[i].charAt(j);
                if (c == '.') continue;
                antennas.putIfAbsent(c, new ArrayList<>());
                antennas.get(c).add(new Position(j, i));
            }
        }

        return antennas.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
