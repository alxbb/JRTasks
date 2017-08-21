package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static AtomicInteger priority = new AtomicInteger(Thread.MIN_PRIORITY);

    static {
        Thread.currentThread().setPriority(priority.getAndIncrement());
    }

    private int nextPiority(){
        int out = priority.getAndIncrement();
        if(priority.get() > Thread.MAX_PRIORITY) priority.set(Thread.MIN_PRIORITY);
        return out;
    }

    public MyThread() {
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(Runnable target) {
        super(target);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(String name) {
        super(name);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        Thread.currentThread().setPriority(nextPiority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        Thread.currentThread().setPriority(nextPiority());
    }
}
