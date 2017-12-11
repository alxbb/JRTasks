package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String roman = s.toUpperCase();
        List list = Arrays.asList("M","D","C","L","X","V","I");

        for(char str : roman.toCharArray() ){
            if(!list.contains(String.valueOf(str))) return -1;
        }

        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
            map.put(1000,  "M"); map.put( 900, "CM"); map.put( 500,  "D"); map.put( 400, "CD");
            map.put( 100,  "C"); map.put(  90, "XC"); map.put(  50,  "L"); map.put(  40, "XL");
            map.put(  10,  "X"); map.put(   9, "IX"); map.put(   5,  "V"); map.put(   4, "IV");
            map.put(   1,  "I");
        int sum = 0;
        while(roman.length() > 0){
            for(Map.Entry<Integer, String> pair : map.entrySet()){
                if(roman.startsWith(pair.getValue())) {
                    sum+=pair.getKey();
                    roman = roman.substring(pair.getValue().length());
                    break;
                }
            }
        }

        return sum;
    }
}
