package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File fin = new File(br.readLine());
        String[] wordsIn = null;
        br.close();
        if (!fin.exists()) return;
        BufferedReader bfr = new BufferedReader(new FileReader(fin));

        while(bfr.ready()){
            wordsIn = bfr.readLine().split(" ");
            Collections.addAll(list, wordsIn);
        }

        bfr.close();

        for (int i = 0; i < list.size()-1; i++) {
            String s1 = list.get(i);
            for (String s2 : list.subList(i+1,list.size())){
                StringBuilder sb = new StringBuilder(s2).reverse();
                if(s1.equals(sb.toString())){
                    Pair pair = new Pair();
                    pair.first = s1;
                    pair.second = s2;
                    if(!result.contains(pair)) result.add(pair);
                }
            }
        }
        for (Pair p : result) System.out.println(p);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}
