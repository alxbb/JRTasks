package com.javarush.task.task32.task3203;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.Arrays;

/*
Пишем стек-трейс
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);
    }

    public static String getStackTrace(Throwable throwable) throws IOException {

        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        throwable.printStackTrace(pw);
        return writer.toString();
    }
}