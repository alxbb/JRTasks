package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;
    public static final String DISH_SEPARATOR = ", ";

    Dish(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        boolean isAddSeparator = false;
        String ret = "";
        for (Dish d : Dish.values()) {
            if(isAddSeparator) ret += DISH_SEPARATOR;
            ret += d;
            isAddSeparator = true;
        }
        return ret;
    }

}
