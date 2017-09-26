package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        Integer out;
        if("0x".equalsIgnoreCase(s.substring(0,2))) {
            out = Integer.parseInt(s.substring(2), 16);
        } else if ("0b".equalsIgnoreCase(s.substring(0,2))) {
            out = Integer.parseInt(s.substring(2), 2);
        } else if ("0".equals(s.substring(0,1))) {
            out = Integer.parseInt(s, 8);
        } else
            out = Integer.parseInt(s, 10);

        return "" + out;
    }
}
