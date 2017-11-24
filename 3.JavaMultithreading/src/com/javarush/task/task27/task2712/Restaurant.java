package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();


    public static void main(String[] args) throws IOException {
        List<Tablet> tablets = new ArrayList<>();
        Cook cook1 = new Cook("Миша");
        Cook cook2 = new Cook("Гриша");
        Waiter waiter = new Waiter();
//        StatisticManager.getInstance().register(cook1);
//        StatisticManager.getInstance().register(cook2);
//        OrderManager orderManager = new OrderManager();

        Stream.iterate(1, i->i+1).limit(5).forEach( i->tablets.add(new Tablet(i)));
        tablets.forEach(t->t.setQueue(orderQueue));
//        tablets.stream().forEach(t->t.addObserver(orderManager));
        cook1.setQueue(orderQueue);
        cook2.setQueue(orderQueue);
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread test = new Thread(new RandomOrderGeneratorTask(tablets,ORDER_CREATING_INTERVAL));
        Thread tCook1 = new Thread(cook1);
        Thread tCook2 = new Thread(cook2);
        tCook1.setDaemon(true);
        tCook2.setDaemon(true);
        test.start();
        tCook1.start();
        tCook2.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
//        ConsoleHelper.writeMessage("%n>>>>>>>>>>>>>>>>>>>> Advert Statictics >>>>>>>>>>>>>>>>>>>>");
        directorTablet.printAdvertisementProfit();
//        ConsoleHelper.writeMessage("%n>>>>>>>>>>>>>>>>>>>> CookWorkLoading Statictics >>>>>>>>>>>>>>>>>>>>");
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
