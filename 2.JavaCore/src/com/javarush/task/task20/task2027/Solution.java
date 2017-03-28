package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/* 
Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for( Word w : detectAllWords(crossword, "home", "same")) System.out.println(w);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> wordList = new ArrayList<>();

        for (String s : words){
            wordList.add(findWord( crossword, s));
        }
        return wordList;
    }

    public static Word findWord(int[][] crw, String w){
        Word word = new Word(w);
        int sX = 0, sY = 0;
        int eX, eY;
        int mX, mY;

        char[] chars = w.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < crw.length; j++) {
                for (int k = 0; k < crw[j].length; k++) {
                    if(i == 0 && crw[j][k] == chars[i]){
                        System.out.printf("1 j = %d k = %d %c %n", j, k, chars[i] );
                        word.setStartPoint(k, j);
                        sX = j; sY = k;
                        mX = j; mY = k;
                        eX = j; eY = k;
                    } else if (crw[j][k] == chars[i]){
                        mX = j; mY = k;
                        if (abs(mX-j) <= 1 && abs(mY-k) <= 1) {
                            System.out.printf("2 j = %d k = %d %c %n", j, k, chars[i]);
                            mX = j; mY = k;
                            eX = j; eY = k;
                            word.setEndPoint(k,j);
                        }else {
                            continue;
                        }

                    }
                    if (i==chars.length-1) continue;
                }
                if (i==chars.length-1) continue;
            }
        }

        return word;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
