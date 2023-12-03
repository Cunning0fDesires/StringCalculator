package org.example.calculator.delimiterparsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultipleCharDelimiterParser implements DelimiterParser {

    public static final String REGEX = "\\[([^\\[\\]]+)\\]";

    @Override
    public String parse(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        int newLineIndex = input.indexOf("\n");
        String extractedData = input.substring(newLineIndex + 1);
        while (matcher.find()) {
            String delimiterGroup = matcher.group();
            String delimiter = delimiterGroup.substring(1, delimiterGroup.length() - 1);
            extractedData = extractedData.replaceAll(Pattern.quote(delimiter), ",");
        }
        return extractedData;
    }
}
