package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int i = 0;
        int d = 0;
        int l = 0;
        int u = 0;

        do {
            int r = random.nextInt('0', 'z');
            if (r >= '0' && r <= '9') {
                baos.write(r);
                d++;
                i++;
            } else if (r >= 'A' && r <= 'Z') {
                baos.write(r);
                u++;
                i++;
            } else if (r >= 'a' && r <= 'z') {
                baos.write(r);
                l++;
                i++;
            }
            if (i == 8 && (d == 0 || u == 0 || l == 0) ){
                i = 0;
                d = 0;
                l = 0;
                u = 0;
                baos.reset();
            }
        } while (i < 8);

        return baos;
    }
}