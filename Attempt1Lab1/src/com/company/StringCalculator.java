package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static Object add(String text) {
        if(text.isEmpty()) return 0;
        else if(text.contains(",")||text.contains("\n")||text.contains("//")) {
            int sumOfNumbers = 0;
            String[] numbers = null;
            String[] YoursDelimiters = null;
            ArrayList<Integer> negativeNumbers = new ArrayList<Integer>();
            if(Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(text).matches()){
                Matcher m = Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(text);
                if (m.find()) {
                    YoursDelimiters = m.group(1).split("\\]\\[");
                }
                m.matches();
                text = m.group(2);
                if(text.contains("\n")){
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        return "New line delimiter are not allowed with yours delimiters";
                    }
                }
                StringBuilder sb = new StringBuilder();
                Comparator<String> lengthComparator = new Comparator<String>() {
                    @Override                           //перевизначення compare
                    public int compare(String s1, String s2) {
                        return s2.length()-s1.length(); //спадання кількості знаків рздільників
                    }
                };
                Arrays.sort(YoursDelimiters, lengthComparator);
                for (String delimiter:YoursDelimiters) {
                    sb.append("|");
                    for (int i=0;i<delimiter.length();i++){
                        if(!Character.isDigit(delimiter.charAt(i))){
                            sb.append("\\");
                        }
                        sb.append(delimiter.charAt(i));
                    }
                }
                sb = new StringBuilder(sb.substring(1));
                numbers = text.split(String.valueOf(sb));
                for (String number:numbers) {
                    if(!number.isEmpty()){
                        if(((!Character.isDigit(number.charAt(0))&&(number.charAt(0) != '-'))||(!Character.isDigit(number.charAt(number.length()-1))&&(number.charAt(number.length()-1)!='-')))) {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                return "Two different delimiters in a row and first non-digit symbol are not allowed\n";
                            }
                        }
                    }
                }
                text = text.replaceAll(String.valueOf(sb), ",");
            }
            else if(Pattern.compile("//(.*)\n(.*)").matcher(text).matches()){
                Matcher m = Pattern.compile("//(.*)\n(.*)").matcher(text);
                m.matches();
                StringBuilder YoursDelimiter = new StringBuilder();
                YoursDelimiter.append("\\");
                YoursDelimiter.append(m.group(1));
                text = m.group(2);
                if(text.contains("\n")){
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        return "New line delimiter are not allowed with yours delimiters";
                    }
                }
                numbers = text.split(String.valueOf(YoursDelimiter));
                for (String number:numbers) {
                    if(!number.isEmpty()){
                        if(((!Character.isDigit(number.charAt(0))&&(number.charAt(0) != '-'))||(!Character.isDigit(number.charAt(number.length()-1))&&(number.charAt(number.length()-1)!='-')))) {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                return "Two different delimiters in a row and first non-digit symbol are not allowed\n";
                            }
                        }
                    }
                }
                text = text.replaceAll(String.valueOf(YoursDelimiter), ",");
            }
            else{
                numbers = text.split("\n");
                for (String number:numbers) {
                    if(!number.isEmpty()){
                        if(((!Character.isDigit(number.charAt(0))&&(number.charAt(0) != '-'))||(!Character.isDigit(number.charAt(number.length()-1))&&(number.charAt(number.length()-1)!='-')))) {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                return "Two different delimiters in a row and first non-digit symbol are not allowed\n";
                            }
                        }
                    }
                }
            }
            numbers = text.replaceAll("\n",",").split(",");
            for (String number:numbers) {
                if(!number.isEmpty()){
                    if(Integer.parseInt(number)<0){
                        negativeNumbers.add(Integer.parseInt(number));
                    }
                }
            }
            if(!negativeNumbers.isEmpty()) {
                try {
                    throw new NegativesNumbersNotAllowedException("Negative numbers are not allowed\n");
                }
                catch(NegativesNumbersNotAllowedException e){
                    return e.getMessage()+negativeNumbers;
                }
            }
            else{
                for (String number : numbers) {
                    if(!number.isEmpty()) {
                        int num = Integer.parseInt(number);
                        if (num <= 1000) sumOfNumbers += num;
                    }
                }
                return sumOfNumbers;
            }
        }
        else return Integer.parseInt(text);
    }
}
