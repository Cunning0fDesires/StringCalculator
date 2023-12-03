import org.example.calculator.StringCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void whenEmptyString_returnZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void whenSingleNumber_returnIt() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void whenTwoNumbersWithCommaDelimiter_calculateSumCorrectly() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void whenNewlineDelimiter_calculateSumCorrectly() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    void whenCustomDelimiter_calculateSumCorrectly() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void whenCustomDelimiterWithBrackets_calculateSumCorrectly() {
        assertEquals(6, StringCalculator.add("//[|||]\n1|||2|||3"));
    }

    @Test
    public void whenMultipleDelimiters_calculateSumCorrectly() {
        assertEquals(6, StringCalculator.add("//[|][%]\n1|2%3"));
    }

    @Test
    public void whenMultipleDelimitersWithDifferentLengths_calculateSumCorrectly() {
        assertEquals(6, StringCalculator.add("//[***][%]\n1***2%3"));
    }

    @Test
    public void whenNegativeNumbers_throwException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("-1,2"));
        assertEquals("Negatives not allowed", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("2,-4,3,-5"));
        assertEquals("Negatives not allowed", exception.getMessage());
    }

    @Test
    public void whenNumbersGreaterThan1000_notCounted() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }
}
