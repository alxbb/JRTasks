package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = new Integer(100);
        List<Boolean> list = new ArrayList<>();
        list.addAll((Collection<? extends Boolean>) o);
    }

    public void methodThrowsNullPointerException() {
        List<String> list = null;
        String s = list.get(0);
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsClassCastException();
        new VeryComplexClass().methodThrowsNullPointerException();

    }
}
