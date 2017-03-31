package com.javarush.task.task22.task2202;

/* 
Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
//        System.out.println(getPartOfString("    "));
    }

    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();
        if (string.trim().length() == 0 ) throw new TooShortStringException();

        String[] words = string.split(" ");
        String out = "";
        int pos = string.indexOf(' ');
            pos = string.indexOf(' ', pos +1);
            pos = string.indexOf(' ', pos +1);
            pos = string.indexOf(' ', pos +1);
            if (pos == -1) throw new TooShortStringException();

        if(words.length < 5) return null;
        for (int i = 1; i < 5; i++) {
            out += words[i] + " ";
        }

        return out.trim();
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
