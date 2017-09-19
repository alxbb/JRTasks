package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer{
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order argTmp = (Order) arg;
        String out = String.format("Start cooking - %s, cooking time %dmin", argTmp, argTmp.getTotalCookingTime() );
        ConsoleHelper.writeMessage(out);
        StatisticManager
                .getInstance()
                .register(new CookedOrderEventDataRow(
                          argTmp.getTablet().toString()
                        , this.name
                        , argTmp.getTotalCookingTime()
                        , argTmp.getDishes()
                        ));
        setChanged();
        notifyObservers(argTmp);
    }
}
