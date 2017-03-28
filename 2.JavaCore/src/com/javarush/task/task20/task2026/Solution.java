package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] b = new byte[a.length][];
        for (int i = 0; i < a.length ; i++) {
            b[i] = new byte[a[i].length];
            System.arraycopy(a[i], 0, b[i], 0, a[i].length);
        }
        int count = 0;

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if(b[i][j] == 1) {count++; wipeRectangle(b, i, j);}
            }
        }
        return count;
    }

    public static void wipeRectangle( byte[][] r, int x, int y){
        for (int i = x; i < r.length; i++) {
            if(r[i][y]==0) break;
            for (int j = y; j < r[i].length; j++) {
                if(r[i][j] == 1) r[i][j] = 0;
                else break;
            }
        }
    }
}
