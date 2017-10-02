package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder sOut = new StringBuilder(number + " =");
        List<Integer> lint = new ArrayList<>();
        int before;
        int result = number;
        int rest;

        while(true){
            before = result;
            rest = before%3;
            result = before / 3;
            if(rest == 2) result+=1;
            lint.add(new Integer(rest));
            if(result < 3) {
                lint.add(result);
                break;
            }
        }

        for (int i = 0; i < lint.size(); i++) {
            if(i < lint.size() - 1 ){
                switch (lint.get(i)){
                    case 0: continue;
                    case 1: sOut.append(" + " + (int)Math.pow(3, i)); break;
                    case 2: sOut.append(" - " + (int)Math.pow(3, i)); break;
                }
            } else {
                switch (lint.get(i)){
                    case 0: sOut.append(" + " + (int)Math.pow(3, i)); break;
                    case 1: sOut.append(" + " + (int)Math.pow(3, i)); break;
                    case 2:
                        sOut.append(" - " + (int)Math.pow(3, i));
                        sOut.append(" + " + (int)Math.pow(3, i+1));
                        break;
                }
            }
        }
//        lint.stream().forEach(s->System.out.print(s + " "));
//        System.out.println();
        System.out.println(sOut);
    }
}