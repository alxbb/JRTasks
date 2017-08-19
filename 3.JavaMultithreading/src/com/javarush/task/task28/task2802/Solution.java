package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {
    public static class AmigoThreadFactory implements ThreadFactory{
        private static volatile String prevGroupName = "";
        private static AtomicInteger a = new AtomicInteger(0);
        private AtomicInteger b = new AtomicInteger(1);
        final int currentPool;

        public AmigoThreadFactory() {
            currentPool = a.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            String currentGroupName = Thread.currentThread().getThreadGroup().getName();
//            if(!currentGroupName.equals(prevGroupName)) a.incrementAndGet();
            Thread newThread = new Thread(r);
            newThread.setDaemon(false);
            newThread.setPriority(Thread.NORM_PRIORITY);
            newThread.setName(String.format(
                    "%s-pool-%s-thread-%d",
                    currentGroupName,
                    currentPool,
                    b.getAndAdd(1)));
            prevGroupName = currentGroupName;
            return newThread;
        }
    }

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
