package day3;

import java.util.regex.Pattern;

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
}
