package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws IOException {
        List<Tablet> tablets = new ArrayList<>();
        Cook cook1 = new Cook("Миша");
        Cook cook2 = new Cook("Гриша");
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Stream.iterate(1, i->i+1).limit(15).forEach( i->tablets.add(new Tablet(i)));
        tablets.stream().forEach(t->t.addObserver(t.number%2 == 0 ? cook1 : cook2));

        Thread test = new Thread(new RandomOrderGeneratorTask(tablets,ORDER_CREATING_INTERVAL));
        test.start();

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
