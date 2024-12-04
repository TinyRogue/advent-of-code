package day4;

import java.util.ArrayList;
import java.util.List;

public class WordCounter {
    public static long count(String sought, String[] grid) {
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

    private static boolean check(String sought, String line) {
        return sought.equals(line);
    }

    private static List<String> buildCases(String[] grid, int row, int col, int len) {
        var strings = new ArrayList<String>();

        var canBuildRight = col + len <= grid[0].length();
        var canBuildDown = row + len <= grid.length;
        var canBuildUp = row + 1 - len >= 0;

        if (canBuildRight) {
            String right = grid[row].substring(col, col + len);
            String rightReversed = new StringBuilder(right).reverse().toString();
            strings.add(right);
            strings.add(rightReversed);
        }

        if (canBuildDown) {
            StringBuilder vertical = new StringBuilder();
            for (int i = 0; i < len; i++) {
                vertical.append(grid[row + i].charAt(col));
            }
            strings.add(vertical.toString());
            strings.add(vertical.reverse().toString());
        }

        if (canBuildUp && canBuildRight) {
            StringBuilder diagonalUpRight = new StringBuilder();
            for (int i = 0; i < len; i++) {
                diagonalUpRight.append(grid[row - i].charAt(col + i));
            }
            strings.add(diagonalUpRight.toString());
            strings.add(diagonalUpRight.reverse().toString());
        }

        if (canBuildDown && canBuildRight) {
            StringBuilder diagonalDownRight = new StringBuilder();
            for (int i = 0; i < len; i++) {
                diagonalDownRight.append(grid[row + i].charAt(col + i));
            }
            strings.add(diagonalDownRight.toString());
            strings.add(diagonalDownRight.reverse().toString());
        }
        return strings;
    }
}
