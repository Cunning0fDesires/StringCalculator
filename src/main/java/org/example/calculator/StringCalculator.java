package org.example.calculator;

import org.example.calculator.delimiterparsers.DelimiterParser;
import org.example.calculator.delimiterparsers.MultipleCharDelimiterParser;
import org.example.calculator.delimiterparsers.SingleCharDelimiterParser;

public class StringCalculator {

    private static final int LIMIT = 1000;
    private static final String STANDARD_DELIMITER = "[,\\n]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static DelimiterParser delimiterParser;

    public static int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            setDelimiterParser(input);
            return parseStringAndSumNumbers(delimiterParser.parse(input));
        } else {
            return parseStringAndSumNumbers(input);
        }
    }

    private static void setDelimiterParser(String input) {
        delimiterParser = input.startsWith(CUSTOM_DELIMITER_PREFIX + "[") ?
                new MultipleCharDelimiterParser() : new SingleCharDelimiterParser();
    }

    private static int parseStringAndSumNumbers(String input) {
        int sum = 0;
        String[] numberStrings = input.split(STANDARD_DELIMITER);
        for (String num : numberStrings) {
            int number = Integer.parseInt(num);
            number = fallbackToZeroIfMoreThanLimit(number);
            sum += number;
        }
        return sum;
    }

    private static int fallbackToZeroIfMoreThanLimit(Integer number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negatives not allowed");
        } else if (number > LIMIT) {
            number = 0;
        }
        return number;
    }
}
