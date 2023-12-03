package org.example.calculator.delimiterparsers;

public class SingleCharDelimiterParser implements DelimiterParser {
    @Override
    public String parse(String input) {
        char inputCustomDelimiter = input.charAt(2);
        return input.substring(4).replace(inputCustomDelimiter, ',');
    }
}
