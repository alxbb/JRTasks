package com.javarush.task.task22.task2211;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки

В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "Нарушение кодировки консоли?"; //only for your testing

    public static void main(String[] args) throws IOException {
        if(args.length < 2) return;
        byte[] in;
        if (!(new File(args[0])).exists()) return;

        FileInputStream  fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        in = new byte[fis.available()];
        Charset win = Charset.forName("Windows-1251");
        Charset utf = Charset.forName("UTF-8");
        fis.read(in);
        String converted = new String(in, utf);
        byte[] out = converted.getBytes(win);
        fos.write(out);
        fis.close();
        fos.close();
    }
}
