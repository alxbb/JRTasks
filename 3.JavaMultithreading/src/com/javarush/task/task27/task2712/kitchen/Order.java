package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        String ret = "";
        if (dishes.size() != 0)
            ret = String.format("Your order: [%s] of %s", orderedString(), tablet.toString());
        return ret;
    }

    private String orderedString() {
        boolean isAddSeparator = false;
        String ret = "";
        for (Dish d : dishes) {
            if(isAddSeparator) ret+=Dish.DISH_SEPARATOR;
            ret += d;
            isAddSeparator = true;
        }
        return ret;
    }

    public int getTotalCookingTime(){
        int duration = 0;
        for(Dish d : dishes) duration+=d.getDuration();
        return duration;
    }
    public boolean isEmpty(){return dishes.size()==0 ? true : false;}

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
