package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(5);
    }

    public void createExpression(int number) {
        StringBuilder sOut = new StringBuilder(number + " =");
        List<Integer> list = new ArrayList<>();
        int ost;
        int result = number;
        int before;

        while (true) {
            before = result;
            ost = result % 3;
            result = result / 3;
            if (ost == 2) result++;
            if (before > 3) {
                list.add(ost);
            } else if(result == 2) {
                list.add(2);
                list.add(1);
                list.add(-1);
            } else {
                list.add(result);
                list.add(-1);
                break;
            }
        }
        list.stream().forEach(System.out::print);
        System.out.println();

        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)) {
                case 0:
                    sOut.append("");
                    break;
                case 1:
                    sOut.append(" + ").append((int) Math.pow(3, i));
                    break;
                case 2:
                    sOut.append(" - ").append((int) Math.pow(3, i));
                    break;
                case -1:
                    sOut.append(" + ").append((int) Math.pow(3, i));
                    break;
            }

        }
        System.out.println(sOut);
    }
//    public void createExpression(int number) {
//        List<String> triza = new ArrayList<>();
//        StringBuilder sOut = new StringBuilder();
//        sOut.append(number).append(" =");
//        int result = number;
//        int ost = 0;
//        int before;
//        while (true){
//            before = result;
//            ost = result%3;
//            result = result/3;
//            if(ost == 2) result++;
//            if(before > 2){
//                before = result;
//                switch (ost){
//                    case 0: triza.add("0"); break;
//                    case 1: triza.add("+"); break;
//                    case 2: triza.add("-"); break;
//                }
//            } else if(before ==2){
//                triza.add("-");
//                triza.add("+");
//                triza.add("end");
//                break;
//            } else {
//                triza.add("0");
//                triza.add("end");
//                break;
//            }
//        }
//        triza.stream().forEach(System.out::print);
//        System.out.println();
//        for (int i = 0; i < triza.size() ; i++) {
//            System.out.println("i = " + i + " " + triza.get(i));
//            switch (triza.get(i)){
//                case "-":   sOut.append(" - " + (int)Math.pow(3,i));   break;
//                case "+":   sOut.append(" + " + (int)Math.pow(3,i));   break;
//                case "+-":  sOut.append(" - " + (int)Math.pow(3,i));   break;
//                case "0": break; //  sOut.append(" + " + 0*(int)Math.pow(3,i)); break;
//                case "end": sOut.append(" + " + (int)Math.pow(3,i));   break;
//                default: continue;
//            }
//        }
//        System.out.println();
//        System.out.println(sOut);
//    }
}