package com.javarush.task.task22.task2209;

import java.io.*;

/*
Составить цепочку слов
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
Вывести полученную строку на экран.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File fin = new File(br.readLine());
        if (!fin.exists()) return;
        br.close();
        String[] words;

        BufferedReader bFr = new BufferedReader(new FileReader(fin));
        words = bFr.readLine().split(" ");
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if(words.length == 0) return new StringBuilder("");
        StringBuilder sb = new StringBuilder(words[0]);
        boolean rpt = true;
        while (rpt) {
            for (int i = 1; i < words.length; i++) {
                if(sb.indexOf(words[i]) >= 0) continue;
                String s1 = words[i].substring(0, 1).toUpperCase();
                String s2 = sb.substring(0, 1).toUpperCase();
                String e1 = words[i].substring(words[i].length() - 1).toUpperCase();
                String e2 = sb.substring(sb.length() - 1).toUpperCase();
                if (e1.equals(s2)) sb = sb.insert(0, words[i] + " ");
                else if (s1.equals(e2)) sb = sb.append(" " + words[i]);
            }
            for(String s : words){
                if(sb.indexOf(s) == -1 ){ rpt = true; break;}
                else rpt = false;
            }
        }
        return sb;
    }
}
