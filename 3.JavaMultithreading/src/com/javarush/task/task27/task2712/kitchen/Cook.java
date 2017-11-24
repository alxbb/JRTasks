package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy = false;
    private LinkedBlockingQueue<Order> queue;


    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void startCookingOrder(Order order) {
        if(order == null) return;
        busy = true;
        String out = String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime());
        ConsoleHelper.writeMessage(out);
        StatisticManager
                .getInstance()
                .register(new CookedOrderEventDataRow(
                        order.getTablet().toString()
                        , this.name
                        , order.getTotalCookingTime()
                        , order.getDishes()
                ));
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public void run() {
        Order order;
        while (true) {
//            System.out.println(this.toString() + queue.size() + this.isBusy());
            if (queue.size()> 0 && !this.isBusy()) {
                if((order = queue.poll())!= null) this.startCookingOrder(order);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
