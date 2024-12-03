package day3;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MulParser {

    public static Long parse(String input) {
        var mulPattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        var digitPattern = Pattern.compile("\\d+");

        return mulPattern.matcher(input).results().map(mul -> digitPattern.matcher(mul.group())
                .results()
                .map(el -> Long.parseLong(el.group()))
                .reduce(1L, (subtotal, current) -> subtotal * current)
        ).reduce(0L, Long::sum);
    }

    public static Long instructedParse(String input) {
        var doParts = Arrays.stream(input.split("do\\(\\)")).map(toCut -> toCut.split("don't\\(\\)")[0]).collect(Collectors.joining());
        return parse(doParts);
    }
}
