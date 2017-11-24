package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/
public class Solution {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        System.out.println("> isEmpty = " + rbt.isEmpty());
        System.out.println("> current = " + rbt.current);
        System.out.println("> rbt = " + rbt);
        System.out.println("--------------------------");
        for (int i = 0; i < 5 ; i++) {
            rbt.insert(i);
            System.out.println("> isEmpty = " + rbt.isEmpty());
            System.out.println("> current = " + rbt.current);
            System.out.println("> rbt = " + rbt);
        }

        System.out.println("--------------------------");
        System.out.println(rbt.isEmpty());
    }
}
