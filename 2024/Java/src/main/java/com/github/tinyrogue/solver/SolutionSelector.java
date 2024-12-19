package com.github.tinyrogue.solver;

import com.github.tinyrogue.data.ResourceLoader;
import com.github.tinyrogue.day1.DistanceMeasure;
import com.github.tinyrogue.day1.SimilarityChecker;
import com.github.tinyrogue.day2.NonTolerantReportAnalyser;
import com.github.tinyrogue.day2.TolerantReportAnalyser;
import com.github.tinyrogue.day3.MulParser;
import com.github.tinyrogue.day4.WordCounter;
import com.github.tinyrogue.day5.PrintQueueValidator;
import com.github.tinyrogue.day6.GuardMovementPredictor;
import com.github.tinyrogue.day7.OperationChecker;
import com.github.tinyrogue.day7.operators.Add;
import com.github.tinyrogue.day7.operators.Concat;
import com.github.tinyrogue.day7.operators.Multiple;
import com.github.tinyrogue.day8.AntinodeMeter;

import java.util.Arrays;
import java.util.List;

public class SolutionSelector {
    private SolutionSelector() {
    }

    public static Solution select(int day) {
        var rawData = ResourceLoader.ofDay(day).getPuzzleInput();
        return switch (day) {
            case 1 -> part -> {
                var dataParser = new com.github.tinyrogue.day1.DataParser(rawData);
                if (part == Part.FIRST) {
                    return DistanceMeasure.totalDistanceBetween(dataParser.getFirstLocationIds(), dataParser.getSecondLocationIds());
                }
                return SimilarityChecker.score(dataParser.getFirstLocationIds(), dataParser.getSecondLocationIds());
            };
            case 2 -> part -> {
                var dataParser = new com.github.tinyrogue.day2.DataParser(rawData);
                if (part == Part.FIRST) {
                    return new NonTolerantReportAnalyser().getSafeReportsNumber(dataParser.getReports());
                }
                return new TolerantReportAnalyser().getSafeReportsNumber(dataParser.getReports());
            };
            case 3 -> part -> {
                if (part == Part.FIRST) {
                    return MulParser.parse(rawData);
                }
                return MulParser.instructedParse(rawData);
            };
            case 4 -> part -> {
                if (part == Part.FIRST) {
                    return WordCounter.count("XMAS", rawData.lines().toArray(String[]::new));
                }
                return WordCounter.countXShaped("MAS", rawData.lines().toArray(String[]::new));
            };
            case 5 -> part -> {
                var dataParser = new com.github.tinyrogue.day5.DataParser(rawData);
                var printQueueValidator = new PrintQueueValidator(dataParser.getRules());
                var parsedSequence = Arrays.stream(dataParser.getUpdates()).map(PrintQueueValidator::parseSequence);
                if (part == Part.FIRST) {
                    return parsedSequence
                            .filter(printQueueValidator::isValid)
                            .reduce(0L, (subtotal, updateSequence) -> subtotal + updateSequence.get(updateSequence.size() / 2), Long::sum);
                }
                return parsedSequence
                        .filter(sequence -> !printQueueValidator.isValid(sequence))
                        .map(printQueueValidator::correctOrdering)
                        .reduce(0L, (subtotal, updateSequence) -> subtotal + updateSequence.get(updateSequence.size() / 2), Long::sum);
            };
            case 6 -> part -> {
                var map = rawData.lines().toArray(String[]::new);
                if (part == Part.FIRST) {
                    return GuardMovementPredictor.pathTravelledLength(map);
                }
                return GuardMovementPredictor.countLoopingObstructions(map);
            };
            case 7 -> part -> {
                var dataParser = new com.github.tinyrogue.day7.DataParser(rawData);
                if (part == Part.FIRST) {
                    return OperationChecker.calibrationValue(dataParser.getOperations(), List.of(new Add(), new Multiple()));
                }
                return OperationChecker.calibrationValue(dataParser.getOperations(), List.of(new Add(), new Multiple(), new Concat()));
            };
            case 8 -> part -> {
                var map = rawData.split("\n");
                if (part == Part.FIRST) {
                    return AntinodeMeter.signalImpact(map, false);
                }
                return AntinodeMeter.signalImpact(map, true);
            };
            default -> throw new InvalidDaySelection(day);
        };
    }
}
