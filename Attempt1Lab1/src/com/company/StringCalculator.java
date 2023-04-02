package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static Object add(String text) {                                                 //Тип обєкт для тощо щоб метод міг виводити різні типи данних
        if(text.isEmpty()) return 0;
        else if(text.contains(",")||text.contains("\n")||text.contains("//")) {
            int sumOfNumbers = 0;
            String[] numbers;
            String YoursDelimiter = "";
            String[] YoursDelimiters = null;
            ArrayList<Integer> negativeNumbers = new ArrayList<Integer>();
            if(Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(text).matches()){                                    //\\-знак, що позначає наступний символ як текстовий   . - деякий символ, + - повториться 1 чи більше разів, * - 0 або більше
                Matcher m = Pattern.compile("//\\[(.*)\\]\n(.*)").matcher(text);                                  //Регулярний вираз для пошуку роздільника та основного тексту
                if (m.find()) {
                    YoursDelimiters = m.group(1).split("\\]\\[");
                }
                m.matches();                                                                                      //matches - метод що повертає так чи ні, потрібен щоб використавувати далі group
                text = m.group(2);
                StringBuilder sb = new StringBuilder();
                for (String delimiter:YoursDelimiters) {
                    sb.append("|");
                    for (int i=0;i<delimiter.length();i++){
                        if(!Character.isDigit(delimiter.charAt(i))){
                            sb.append("\\");
                        }
                        sb.append(delimiter.substring(i,i+1));
                    }
                }
                sb = new StringBuilder(sb.substring(1));
                text = text.replaceAll(String.valueOf(sb), ",");
            }
            else if(Pattern.compile("//(.*)\n(.*)").matcher(text).matches()){
                Matcher m = Pattern.compile("//(.*)\n(.*)").matcher(text);
                m.matches();
                YoursDelimiter = m.group(1);
                text = m.group(2);
                text = text.replaceAll(YoursDelimiter, ",");
            }
            numbers = text.replaceAll("\n",",").split(",");
            for (String number:numbers) {
                if(!number.isEmpty()) {
                    int num = Integer.parseInt(number);
                    if (num < 0) {
                        negativeNumbers.add(num);
                    }
                }
            }
            if(!negativeNumbers.isEmpty()) {
                try {
                    throw new Exception();
                }
                catch(Exception e){
                    return "Negative numbers are not allowed\n"+negativeNumbers;
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
