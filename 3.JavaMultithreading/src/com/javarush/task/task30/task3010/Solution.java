package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try{
//            args[0] = "19";
            System.out.println(getMinBase(args[0]));
        } catch (NumberFormatException e){
            System.out.println("incorrect");
        } catch (Exception e){/*NOP*/}
    }

    private static int getMinBase(String number){
        int base = 2;
        char max = 2;
        char[] chars = number.toUpperCase().toCharArray();
        for(char c : chars) {
            if (isDigit(c)) {
                if (max < c) max = c;
            } else if (isLetter(c)) {
                if (max < c) max = c;
            } else {
                throw new NumberFormatException();
            }
        }
        if(isDigit(max)) base = (max - '/') < 2 ? 2 : max - '/';
        else if(isLetter(max)) base = max - '@' + 10;

        return base;
    }
    private static boolean isDigit(char c){
        return (c >= '0' && c <= '9') ? true : false;
    }
    private static boolean isLetter(char c){
        return (c >= 'A' && c <= 'Z') ? true : false;
    }
}