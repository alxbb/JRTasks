package com.javarush.task.task20.task2025;

/* 
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        ArrayList<Long> list = new ArrayList<>();

        for (long i = 0; i < N; i++) {
            long res = countSummDigits(i);
            if(i == res) list.add(res);
        }

        result = new long[list.size()];
        for (int i = 0; i < list.size() ; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static long countSummDigits(long digit){
        long in = abs(digit);
        int length = countDigits(digit);
        long sum = 0;

        for (int i = 1; i <= length; i++) {
            long cur = in%10;
            //System.out.println(length + " " + cur + " " + i);
            sum += pow(cur,length);
            in /= 10;
        }
        return sum;
    }

    public static int countDigits(long digit){
        int numDigits = 1;
        if( digit > 999_999_999) { numDigits = 10;
            if (digit > 999_999_999_999_999_999L ) numDigits = 19;
            else if (digit >  99_999_999_999_999_999L ) numDigits = 18;
            else if (digit >   9_999_999_999_999_999L ) numDigits = 17;
            else if (digit >     999_999_999_999_999L ) numDigits = 16;
            else if (digit >      99_999_999_999_999L ) numDigits = 15;
            else if (digit >       9_999_999_999_999L ) numDigits = 14;
            else if (digit >         999_999_999_999L ) numDigits = 13;
            else if (digit >          99_999_999_999L ) numDigits = 12;
            else if (digit >           9_999_999_999L ) numDigits = 11;
        } else {
            if (digit > 99_999_999 ) numDigits = 9;
            else if (digit >  9_999_999 ) numDigits = 8;
            else if (digit >    999_999 ) numDigits = 7;
            else if (digit >     99_999 ) numDigits = 6;
            else if (digit >      9_999 ) numDigits = 5;
            else if (digit >        999 ) numDigits = 4;
            else if (digit >         99 ) numDigits = 3;
            else if (digit >          9 ) numDigits = 2;
        }

//        long dummy = abs(digit);
//        while ((dummy /= 10) > 0 ) numDigits++;
        return numDigits;
    }


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        long usedBytesS = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println(usedBytesS);
        ArrayList<String> al = new ArrayList<>();

//        long[] res = getNumbers(100000000);
//
//
//        for (int i = 0; i < res.length ; i++) {
//            System.out.println(res[i]);
//        }

        System.out.println('•');
        System.out.println(Long.MAX_VALUE);
        for (long i = 0; i < Long.MAX_VALUE/100000000L; i++) {
            countDigits(i);
        }

        long stop  = (System.currentTimeMillis() - start);
        System.out.println(stop *1.0 / 1000);
        long usedBytesE = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println((usedBytesE-usedBytesS)/1024);
        System.out.println('\7');
//        System.out.println(countSummDigits(222));
    }
}
