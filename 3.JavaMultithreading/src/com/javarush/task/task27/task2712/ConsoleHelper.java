package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static final String START_STRING = "Выберите желаемые блюда из списка:";
    private static final String EXIT_STRING  = "Для завершения заказа введите exit.";
    private static final String ERROR_STRING = "Указанное блюдо отсутствует в меню, введите корректное название.";

    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {return br.readLine();}

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> chosenDishes = new ArrayList<>();
        String input = "";
        writeMessage(START_STRING);
        writeMessage(Dish.allDishesToString());

        while ( !"exit".equalsIgnoreCase(input = readString()) )
            try {
                if(Dish.valueOf(input).ordinal()>=0 ) chosenDishes.add(Dish.valueOf(input));
            } catch (IllegalArgumentException e) {
                writeMessage(ERROR_STRING);
            }

        return chosenDishes;
    }
}
