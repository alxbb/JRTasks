package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        Path path = Paths.get(args[0]);
        if (!Files.isRegularFile(path)) return;
        TreeSet<String> set = new TreeSet<>();
        List<String> list = Files.readAllLines(path);
        for (String s : list) {
            for (char c : s.toLowerCase().toCharArray()) {
                if (c >= 'a' && c <= 'z') set.add(String.valueOf(c));
            }
        }

        set.stream().limit(5).forEach(System.out::print);
    }
}
