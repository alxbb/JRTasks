package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        if(args.length < 3) return;
        File file = new File(args[0]);
        long offset = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        if(offset > raf.length()) raf.seek(raf.length());
        else raf.seek(offset);

        raf.write(text.getBytes());

        raf.close();
    }
}
