package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".")
возвращает
{"level22", "lesson13", "task01"}
*/
public class Solution {
    public static void main(String[] args) {
        String s = "level22.lesson13.task01";
        String[] ss = getTokens(s, ".");

        for (String sss : ss) System.out.println(sss);
    }
    public static String [] getTokens(String query, String delimiter) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(query, delimiter);
        while (st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size() ; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
