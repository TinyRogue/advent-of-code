package com.github.tinyrogue.data;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ResourceLoader {
    private static final String PUZZLE_FILENAME = "puzzle_input.txt";
    private static final String PART_1_FILENAME = "test_part_1.txt";
    private static final String PART_2_FILENAME = "test_part_2.txt";
    private final Path puzzleInputPath;
    private final Path part1Path;
    private final Path part2Path;
    private String puzzleData;
    private String part1Data;
    private String part2Data;

    private ResourceLoader(Integer day) {
        var dayPath = "day" + day;
        this.puzzleInputPath = Paths.get(dayPath, PUZZLE_FILENAME);
        this.part1Path = Paths.get(dayPath, PART_1_FILENAME);
        this.part2Path = Paths.get(dayPath, PART_2_FILENAME);
    }

    public static ResourceLoader ofDay(int day) {
        if (day < 1 || day > 25) {
            throw new IllegalArgumentException("Advent of Code has 25 days! Day " + day + " was provided.");
        }
        return new ResourceLoader(day);
    }

    public String getPuzzleInput() {
        if (puzzleData == null) {
            puzzleData = load(puzzleInputPath.toString()).orElseThrow(
                    () -> new IllegalArgumentException("File " + puzzleInputPath + " with puzzle input was not found in resources.")
            );
        }
        return puzzleData;
    }

    public String getTestPart1() {
        if (part1Data == null) {
            part1Data = load(part1Path.toString()).orElseThrow(
                    () -> new IllegalArgumentException("File " + part1Path + " with part 1 data was not found in resources.")
            );
        }
        return part1Data;
    }

    public String getTestPart2() {
        if (part2Data == null) {
            part2Data = load(part2Path.toString()).orElseThrow(
                    () -> new IllegalArgumentException("File " + part2Path + " with part 2 data was not found in resources.")
            );
        }
        return part2Data;
    }

    private Optional<String> load(String resourcePath) {
        var classLoader = ResourceLoader.class.getClassLoader();
        try (var is = classLoader.getResourceAsStream(resourcePath)) {
            if (is == null) {
                return Optional.empty();
            }
            return Optional.of(new String(is.readAllBytes()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
