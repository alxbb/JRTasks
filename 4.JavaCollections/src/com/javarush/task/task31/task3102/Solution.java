package com.javarush.task.task31.task3102;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new PriorityQueue<>();
        List<String> list = new ArrayList<>();
        queue.add(new File(root));

        while(!queue.isEmpty()){
            File[] files = queue.poll().listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if(file.isDirectory()) queue.add(file);
                else list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("c:/temp");
        list.stream().forEach(System.out::println);
    }
}
