package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(br.readLine());
        if(!Files.isDirectory(path)) {
            System.out.println(path.toString() + " - не папка");
            return;
        }

        System.out.printf("Всего папок - %d%n" , getDirStatistics(path, "DIRS" ));
        System.out.printf("Всего файлов - %d%n", getDirStatistics(path, "FILES"));
        System.out.printf("Общий размер - %d%n", getDirStatistics(path, "SIZE" ));
    }

    public static long getDirStatistics(Path path, String mode) throws IOException {
        final long[] dirs  = {0};
        final long[] files = {0};
        final long[] size  = {0};

        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirs[0]++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                files[0]++;
                size [0] += attrs.size();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });

        switch (mode){
            case "DIRS":  return dirs[0]-1;
            case "FILES": return files[0];
            case "SIZE":  return size[0];
        }

        return 0;
    }
}
