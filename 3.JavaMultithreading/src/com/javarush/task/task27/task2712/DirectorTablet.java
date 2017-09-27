package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit(){
        long total = 0;
        Map<String,Long> map = StatisticManager.getInstance().getAdvertisementProfit();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Long> pair : map.entrySet()){
            total += pair.getValue();
            list.add(pair.getKey() + " - " + pair.getValue());
        }

        Collections.sort(list,(s1,s2)->(s2.compareTo(s1)));
        list.add("Total - " + total);
        for(String s : list) ConsoleHelper.writeMessage(s);
    }

    public void printCookWorkloading(){
        Map<String,Map<String, Integer>> map = StatisticManager.getInstance().getCookBusy();
        map.keySet();

    }
    public void printActiveVideoSet(){}
    public void printArchivedVideoSet(){}
}
