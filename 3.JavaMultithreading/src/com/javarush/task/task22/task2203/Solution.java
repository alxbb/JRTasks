package com.javarush.task.task22.task2203;

/* 
Между табуляциями

Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.

*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        int sIdx;
        int eIdx;
        if ( string == null ) throw new TooShortStringException();
        if ( (sIdx = string.indexOf('\t')) == -1) throw new TooShortStringException();
        if ( (eIdx = string.indexOf('\t', sIdx+1)) == -1) throw new TooShortStringException();

        return string.substring(sIdx+1, eIdx);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
