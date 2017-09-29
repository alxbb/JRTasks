package com.javarush.task.task30.task3012;

import java.util.stream.*;
import java.lang.Integer.*;
/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        System.out.println( number + " >>>>> " +Integer.toString(number,3));
        Stream.of(Integer.toString(number,3).toCharArray())
                .forEach(s->System.out.println(Integer.toString(Integer.parseInt(String.valueOf(s)),2)+" "));
    }
}