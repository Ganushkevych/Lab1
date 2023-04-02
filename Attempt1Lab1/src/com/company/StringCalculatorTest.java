package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }
    @Test
    public void shouldReturnNumberOnNumber() {
        assertEquals(1, StringCalculator.add("1"));
    }
    @Test
    public void shouldReturnSumOfTwoNumbers() {
        assertEquals(3, StringCalculator.add("1,2"));
    }
    @Test
    public void shouldReturnSumOfNumbers() {
        assertEquals(12, StringCalculator.add("1,2,4,5"));
    }
    @Test
    public void shouldReturnSumWithNewLineAsDelimiter() {
        assertEquals(12, StringCalculator.add("1,2\n4,5"));
    }
    @Test
    public void shouldReturnSumWithYoursDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumber1() throws Exception {
        assertEquals("Negative numbers are not allowed\n[-1, -3]", StringCalculator.add("-1,2,-3"));
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumber2() throws Exception {
        assertEquals("Negative numbers are not allowed\n[-3]", StringCalculator.add("//g\n1g2g-3"));
    }
    @Test
    public void shouldReturnSumAvoidingNumberThatMoreThanThousand() {
        assertEquals(1999, StringCalculator.add("1000,999,1001"));
    }
    @Test
    public void shouldReturnSumWithMultipliedDelimiter() {
        assertEquals(6, StringCalculator.add("//[*]\n1*2**3"));
    }
    @Test
    public void shouldReturnSumWithFewDelimiters() {
        assertEquals(62, StringCalculator.add("//[*][:][?]\n1*1:2:?**5*53"));
    }
    @Test
    public void shouldReturnSumWithFewMultipliedDelimiters() {
        assertEquals(6, StringCalculator.add("//[*&^][;:][:%?*]\n1*&^;:2:%?**&^*&^3"));
    }
}