package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T t1, T t2){
            int compareResult = 0;
            for (int i = 0; i < comparators.length; i++) {
                compareResult = comparators[i].compare(t1, t2);
                if(compareResult == 0) continue;
                else break;
            }
            return compareResult;
        }
    }
}
