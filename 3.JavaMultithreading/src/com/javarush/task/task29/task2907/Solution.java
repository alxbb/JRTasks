package com.javarush.task.task29.task2907;

import java.math.BigDecimal;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        BigDecimal bd1 = new BigDecimal(String.valueOf(v1));
        BigDecimal bd2 = new BigDecimal(String.valueOf(v2));
        return bd1.add(bd2);
    }
}