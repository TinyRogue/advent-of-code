package day8;

import java.util.*;
import java.util.stream.Collectors;

public class AntinodeMeter {

    public static long signalImpact(final String[] map) {
        Map<Character, List<Position>> antennas = getAntennas(map);
        var antinodes = getAntinodePositions(antennas);
        return limitAntinodesOverArea(antinodes, map.length, map[0].length()).size();
    }

    private static Set<Position> limitAntinodesOverArea(final Set<Position> antinodes, final int maxHeight, final int maxWidth) {
        return antinodes.stream()
                .filter(antinode -> antinode.x() >= 0 && antinode.x() < maxWidth && antinode.y() >= 0 && antinode.y() < maxHeight)
                .collect(Collectors.toSet());
    }

    private static Set<Position> getAntinodePositions(final Map<Character, List<Position>> antennas) {
        var antinodes = antennas.keySet().stream().collect(Collectors.toMap(antenna -> antenna, _ -> new HashSet<Position>()));
        for (var antenna : antennas.entrySet()) {
            var positions = antenna.getValue();
            for (int i = 0; i < positions.size(); i++) {
                for (int j = 0; j < positions.size(); j++) {
                    if (i == j) continue;
                    var a1 = positions.get(i);
                    var a2 = positions.get(j);
                    var vDistance = a1.y() - a2.y();
                    var hDistance = a1.x() - a2.x();
                    antinodes.get(antenna.getKey()).add(a1.offset(hDistance, vDistance));
                }
            }
        }
        return antinodes.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
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
