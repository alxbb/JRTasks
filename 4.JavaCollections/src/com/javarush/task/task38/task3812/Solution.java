package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.text.Annotation;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        java.lang.annotation.Annotation anno;
        if( (anno = c.getAnnotation(PrepareMyTest.class)) != null){
            System.out.println(c);
            System.out.println(anno);

            return true;
        }else return false;
    }

    public static boolean printValues(Class c) {
        return true;
    }
}
