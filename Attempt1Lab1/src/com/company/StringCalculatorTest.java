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
        assertEquals(12, StringCalculator.add("1\n2\n4\n5"));
    }
    @Test
    public void shouldReturnSumWithYoursDelimiter() {
        assertEquals(6, StringCalculator.add("//*\n1*2*3"));
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumber() throws Exception {
        assertEquals("Negative numbers are not allowed\n[-3]", StringCalculator.add("1,2,-3"));
        assertEquals("Negative numbers are not allowed\n[-3]", StringCalculator.add("//;\n1;2;-3"));
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
        assertEquals(62, StringCalculator.add("//[*][:][?]\n1**1:2::5???53"));
    }
    @Test
    public void shouldReturnSumWithFewMultipliedDelimiters() {
        assertEquals(10, StringCalculator.add("//[*&^][;:][:%?*]\n1*&^2:%?*:%?*3;:;:;:4"));
        assertEquals(10, StringCalculator.add("//[**][****]\n1****2**3****4"));
        assertEquals(17, StringCalculator.add("//[###][##][#][%%]\n5%%2#5###3##1#1"));
        assertEquals(27, StringCalculator.add("//[**][&][*][%%][%%%][***]\n1*2%%%3***4%%5**9&3"));
    }
    @Test
    public void shouldThrowExceptionOnTwoDelimitersInARow() {
        assertEquals("Two different delimiters in a row and first non-digit symbol are not allowed\n",StringCalculator.add("1\n,2,3"));
    }
    @Test
    public void shouldThrowExceptionOnFirstNonDigitSymbol() {
        assertEquals("Two different delimiters in a row and first non-digit symbol are not allowed\n",StringCalculator.add(",1,2,3"));
    }
}