package com.javarush.task.task32.task3203;

import java.io.*;

/*
Пишем стек-трейс
*/
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
//        System.out.println(text);
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter writer = new StringWriter();
        PrintStream sysOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(baos);
        System.setOut(stream);
        throwable.printStackTrace();
        StringReader reader = new StringReader(baos.toString());
        BufferedReader br = new BufferedReader(reader);
        String s = br.lines().reduce((s1,s2)->s1.concat(s2)).toString();
        writer.write(s);
        System.setOut(sysOut);

        return writer.toString();
    }
}