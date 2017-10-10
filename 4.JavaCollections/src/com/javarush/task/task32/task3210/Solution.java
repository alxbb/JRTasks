package com.javarush.task.task32.task3210;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        if(args.length < 3) return;
        String file = args[0];
        int offset = Integer.parseInt(args[1]);
        String text = args[2];
        byte[] buf = new byte[text.length()];
        String toAppend = "false";

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(offset);
        raf.read(buf,0,buf.length);
        if(Arrays.equals(buf, text.getBytes())) toAppend = "true";

        raf.seek(raf.length());
        raf.write(toAppend.getBytes());
        raf.close();
    }
}
