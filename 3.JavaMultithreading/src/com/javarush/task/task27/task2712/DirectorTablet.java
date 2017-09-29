package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

    public void printAdvertisementProfit(){
        Date tmpDate = new Date();
        long total = 0;
        Map<Long,Long> map = StatisticManager.getInstance().getAdvertisementProfit();
        List<String> list = new ArrayList<>();

        for (Map.Entry<Long, Long> pair : map.entrySet()){
            tmpDate.setTime(pair.getKey());
            total += pair.getValue();
            list.add(sdf.format(tmpDate).toString() + " - " + pair.getValue()/100.0);
        }
        list.add("Total - " + total/100.00);
        for(String s : list) ConsoleHelper.writeMessage(s);
    }

    public void printCookWorkloading(){
        Date tmpDate = new Date();
        Map<String,Integer> innerMap;
        Map<Long,Map<String, Integer>> map = StatisticManager.getInstance().getCookBusy();

        for(Map.Entry<Long, Map<String,Integer>> trio : map.entrySet()){
            tmpDate.setTime(trio.getKey());
            ConsoleHelper.writeMessage(sdf.format(tmpDate));
            innerMap = trio.getValue();
            for(Map.Entry<String,Integer> pair : innerMap.entrySet()){
                ConsoleHelper.writeMessage(pair.getKey() + " - " + pair.getValue() + " min");
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){
        StatisticAdvertisementManager
                .getInstance()
                .getActiveAd()
                .stream()
                .sorted((s1,s2)->(s1.getName().compareTo(s2.getName())))
                .forEach(s-> ConsoleHelper.writeMessage(s.getName() + " - " + s.getHits()));
    }
    public void printArchivedVideoSet(){
        StatisticAdvertisementManager
                .getInstance()
                .getArchivedAd()
                .stream()
                .sorted((s1, s2) -> (s1.getName().toUpperCase().compareTo(s2.getName().toUpperCase())))
                .forEach(s -> ConsoleHelper.writeMessage(s.getName()));
    }
}
