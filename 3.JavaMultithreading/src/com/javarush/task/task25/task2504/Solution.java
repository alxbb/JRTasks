package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод

        for(Thread th : threads){
            Thread.State state = th.getState();
            switch (state){
                case NEW:
                    th.start();
                    break;
                case WAITING:
                case TIMED_WAITING:
                case BLOCKED:
                    th.interrupt();
                    break;
                case RUNNABLE:
                    th.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(th.getPriority());
            }
        }
    }

    public static void main(String[] args) {
    }
}
