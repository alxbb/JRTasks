package com.javarush.task.task36.task3602;

import java.lang.reflect.*;
import java.util.Collections;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class c0 = null;
        
        Class[] classes = Collections.class.getDeclaredClasses();
        for(Class c : classes) {
            int mods = c.getModifiers();
            if(Modifier.isStatic(mods) && Modifier.isPrivate(mods)){
                try {
                    if(c.getMethod("get", int.class)!= null){
                        if(c.getName().toLowerCase().contains("emptylist")) c0 = c;
                    }
                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
                }
            }

        }
        return c0;
    }
}
