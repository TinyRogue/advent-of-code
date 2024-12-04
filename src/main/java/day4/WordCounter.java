package day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WordCounter {
    public static long countXShaped(final String sought, final String[] grid) {
        var counter = 0L;
        for (int i = 0; i <= grid.length - sought.length(); i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                var hasDownDiagonal = buildDownRight(grid, i, j, sought.length()).stream().anyMatch(word -> check(sought, word));
                if (hasDownDiagonal) {
                    counter += buildUpRight(grid, i + sought.length() - 1, j, sought.length()).stream().anyMatch(word -> check(sought, word)) ? 1 : 0;
                }
            }
        }
        return counter;
    }

    public static long count(final String sought, final String[] grid) {
        var counter = 0L;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                if (grid[i].charAt(j) == sought.charAt(0) || grid[i].charAt(j) == sought.charAt(sought.length() - 1)) {
                    counter += buildCases(grid, i, j, sought.length()).stream().filter(word -> check(sought, word)).count();
                }
            }
        }
        return counter;
    }

    private static boolean check(final String sought, final String line) {
        return sought.equals(line);
    }

    private static List<String> buildCases(final String[] grid, final int row, final int col, final int len) {
        return Stream.concat(
                buildHorizontalCases(grid, row, col, len).stream(),
                buildDiagonalCases(grid, row, col, len).stream()
        ).toList();
    }

    private static List<String> buildHorizontalCases(final String[] grid, final int row, final int col, final int len) {
        var cases = new ArrayList<String>();
        var canBuildRight = col + len <= grid[0].length();
        var canBuildDown = row + len <= grid.length;

        if (canBuildRight) {
            String right = grid[row].substring(col, col + len);
            String rightReversed = new StringBuilder(right).reverse().toString();
            cases.add(right);
            cases.add(rightReversed);
        }

        if (canBuildDown) {
            StringBuilder vertical = new StringBuilder();
            for (int i = 0; i < len; i++) {
                vertical.append(grid[row + i].charAt(col));
            }
            cases.add(vertical.toString());
            cases.add(vertical.reverse().toString());
        }
        return cases;
    }

    private static List<String> buildDiagonalCases(final String[] grid, final int row, final int col, final int len) {
        return Stream.concat(
                buildUpRight(grid, row, col, len).stream(),
                buildDownRight(grid, row, col, len).stream()
        ).toList();
    }

    private static List<String> buildDownRight(final String[] grid, final int row, final int col, final int len) {
        var canBuildRight = col + len <= grid[0].length();
        var canBuildDown = row + len <= grid.length;
        if (canBuildDown && canBuildRight) {
            StringBuilder diagonalDownRight = new StringBuilder();
            for (int i = 0; i < len; i++) {
                diagonalDownRight.append(grid[row + i].charAt(col + i));
            }
            return List.of(diagonalDownRight.toString(), diagonalDownRight.reverse().toString());
        }
        return List.of();
    }

    private static List<String> buildUpRight(final String[] grid, final int row, final int col, final int len) {
        var canBuildRight = col + len <= grid[0].length();
        var canBuildUp = row + 1 - len >= 0;

        if (canBuildUp && canBuildRight) {
            StringBuilder diagonalUpRight = new StringBuilder();
            for (int i = 0; i < len; i++) {
                diagonalUpRight.append(grid[row - i].charAt(col + i));
            }
            return List.of(diagonalUpRight.toString(), diagonalUpRight.reverse().toString());
        }
        return List.of();
    }
}
