package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        this.dishes = new ArrayList<>();
        int numRandom = ThreadLocalRandom.current().nextInt(1,Dish.values().length);
        if (numRandom==0){numRandom=1;}
        Dish[] dishMas = Dish.values();
        for(int i = 0; i < numRandom; i++){
            int random = (int)(Math.random() * (Dish.values().length));
            dishes.add(dishMas[random]);
        }
    }
}
