package com.javarush.task.task22.task2201;

import java.text.Format;
import java.util.Formatter;

public class ThisUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else
            if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
                System.out.println(getFormattedStringForSecondThread(t, e, string));
            } else {
                System.out.println(getFormattedStringForOtherThread(t, e, string));
            }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        Formatter fmt = new Formatter();
        fmt.format(string, e.getClass().getSimpleName(), e.getMessage() , t.getName());

        return fmt.toString(); // RuntimeException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : 3#
    }

// java.lang.StringIndexOutOfBoundsException: String index out of range: -1 : TooShortStringSecondThreadException : 2#

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        Formatter fmt = new Formatter();
        fmt.format(string, e.getCause(), e.getMessage(), t.getName());
        return fmt.toString();
    }

// 1# : TooShortStringFirstThreadException : java.lang.StringIndexOutOfBoundsException: String index out of range: -1

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        Formatter fmt = new Formatter();
        fmt.format(string, t.getName(), e.getMessage(), e.getCause());
        return fmt.toString();
    }
}

