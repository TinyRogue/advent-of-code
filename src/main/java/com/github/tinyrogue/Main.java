package com.github.tinyrogue;

import com.github.tinyrogue.solver.Part;
import com.github.tinyrogue.solver.SolutionSelector;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        var day = args.length > 0 ? args[0] : null;
        if (day == null) {
            System.err.println("No Advent day was not specified to kick start. Run the program with a value from 1 to 25.");
            System.exit(1);
        }

        System.out.println("Kick starting day " + day + "!");
        var solution = SolutionSelector.select(Integer.parseInt(day));

        var results = new ArrayList<Long>();
        var executionTimes = new ArrayList<Duration>();
        for (var part : Part.values()) {
            var start = Instant.now();
            results.add(solution.solve(part));
            var end = Instant.now();
            executionTimes.add(Duration.between(start, end));
        }

        System.out.println("Summary: ");
        Function<Object[], String> resultMessage = (r) -> MessageFormat.format("> {0} answer: {1}. Elapsed time: {2}ms.", r);
        for (int i = 0; i < Part.values().length; i++) {
            System.out.println(resultMessage.apply(new Object[]{Part.values()[i].description(), results.get(i), executionTimes.get(i).toMillis()}));
        }
    }
}
