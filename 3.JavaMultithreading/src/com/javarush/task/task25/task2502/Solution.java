package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car()  {
            wheels = new ArrayList<>();
            String[] S = loadWheelNamesFromDB();

            if (S == null) throw new IllegalArgumentException();
            if (S.length != 4) throw new IllegalArgumentException();

            for (String s: S) {
                if(!wheels.contains(Wheel.valueOf(s)))
                    wheels.add(Wheel.valueOf(s));
            }

            if (wheels.size() != 4) throw new IllegalArgumentException();
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
