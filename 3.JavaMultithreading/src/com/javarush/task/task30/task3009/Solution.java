package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("35"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number){
        try{
            Set<Integer> set = new HashSet<>();
            BigInteger num = new BigInteger(number);

            for (int i = 2; i <= 36; i++) {
                if (isPoly(num.toString(i)))
                    set.add(i);
            }
            return set;
        } catch (NumberFormatException e){
            return new HashSet<>();
        }
    }

    private static boolean isPoly(String string){
//        System.out.println(string);
        if (string.length() < 1 ) return false;
        int len = string.length();
        StringBuilder sb = new StringBuilder(string.substring(len/2));
        sb.reverse();
        if (string.length()%2 == 0){
            return string.substring(0, len/2).equals(sb.toString());
        } else {
            return string.substring(0, len/2+1).equals(sb.toString());
        }
    }
}