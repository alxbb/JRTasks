package com.javarush.task.Test;

public class Test2 {
    public static void main(String[] args) {
        double d1 = 0;
        double d2 = 0;
        double d3 = 0;
        double d4 = 0;
        double d5 = 0;
        double d6 = 0;
        double d7 = 0;
        double d8 = 0;
        double d9 = 0;
        System.out.println(Long.MAX_VALUE);
        long stime = System.currentTimeMillis();
        //9_223_372_036_854_775_807

        for (long i = 0; i < Long.MAX_VALUE/10_000_000_000L; i++) {
            d1 = d1 * d1;
//            d1 = d1 * d1;
            d2 = d2 * d2;
            d3 = d3 * d3;
            d4 = d4 * d4;
            d5 = d5 * d5;
            d6 = d6 * d6;
            d7 = d7 * d7;
            d8 = d8 * d8;
            d9 = d9 * d9;

        }
        long etime = System.currentTimeMillis();

        System.out.println(etime - stime);

    }
}
