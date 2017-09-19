package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;
import java.util.Observable;

public class Restaurant {
    public static void main(String[] args) throws IOException {
        Cook cook = new Cook("Миша");
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        Tablet tablet5 = new Tablet(5);
        tablet5.addObserver(cook);
        tablet5.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
