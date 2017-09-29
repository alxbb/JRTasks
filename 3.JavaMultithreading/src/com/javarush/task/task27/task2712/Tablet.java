package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
//    private Order order;
    final int number;

    public Tablet(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
            processOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
//        return order;
    }

    public void createTestOrder() {
        try {
            TestOrder order = new TestOrder(this);
            processOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void processOrder(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
            try{
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }        catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
                StatisticManager
                        .getInstance()
                        .register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
            }

        }
    }
}
