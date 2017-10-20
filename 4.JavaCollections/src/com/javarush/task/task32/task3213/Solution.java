package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        if(reader == null) return new String();
        StringWriter writer = new StringWriter();
        int ch;

        while((ch = reader.read()) != -1){
            writer.write(ch+key);
        }

        return writer.toString();
    }

}
