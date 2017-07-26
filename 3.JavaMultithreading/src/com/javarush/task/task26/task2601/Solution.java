package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] toSort = {13, 8, 15, 5, 17};

//        for (int i : toSort) {
//            System.out.print(i + ", ");
//        }
//        System.out.println("");

        sort(toSort);

//        for (int i : toSort) {
//            System.out.print(i + ", ");
//        }
    }

    public static Integer[] sort(Integer[] array) {
        int mediana;
        Arrays.sort(array);
        if( array.length%2 == 0 ) mediana = (array[array.length/2-1] + array[(array.length+2)/2-1])/2;
        else mediana = array[array.length/2];

//        Comparator<Integer> comp = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return abs(mediana-o1) - abs(mediana-o2) ;
//            }
//        };

        Arrays.sort(array, ((o1, o2) -> abs(mediana-o1) - abs(mediana-o2)) );
        return array;
    }
}
