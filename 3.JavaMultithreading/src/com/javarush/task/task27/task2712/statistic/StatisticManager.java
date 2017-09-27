package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

public class StatisticManager {
    private static StatisticManager manager = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();
    private StatisticManager(){}

    public static StatisticManager getInstance() {
        if (manager == null) manager = new StatisticManager();
        return manager;
    }

    public void register(Cook cook){ this.cooks.add(cook); }
    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public Map<String, Long> getAdvertisementProfit(){ /* TODO*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String date;
        long amount;
        List<EventDataRow> list = statisticStorage.get(EventType.SELECTED_VIDEOS);
        Map<String,Long> map = new HashMap<>();

        for(EventDataRow e : list){
            date = sdf.format(e.getDate()).toUpperCase();
            if(map.containsKey(date)) map.put(date, ((VideoSelectedEventDataRow) e).getAmount() + map.get(date));
            else map.put(date, ((VideoSelectedEventDataRow) e).getAmount());
        }

        return map;
    }

    public Map<String, Map<String,Integer>> getCookBusy(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String date;
        long amount;

        Map<String,Map<String, Integer>> map = new HashMap<>();
        List<EventDataRow> list = statisticStorage.get(EventType.COOKED_ORDER);
        for(EventDataRow e : list){
            CookedOrderEventDataRow c = (CookedOrderEventDataRow) e;
            date = sdf.format(e.getDate());
            if(map.containsKey(date)){
                Map<String,Integer> innerMap = map.get(date);
                if(innerMap.containsKey(c.getCookName())){
                    innerMap.put(c.getCookName(),innerMap.get(c.getCookName())+c.getTime());
                } else {
                    innerMap.put(c.getCookName(),c.getTime());
                }
            } else {
                Map<String, Integer> newMap = new HashMap<>();
                map.put(date, newMap);
                newMap.put(c.getCookName(),c.getTime());
            }
        }

        return map;
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticStorage() {
            for (EventType e: EventType.values()) {
                storage.put(e, new ArrayList<EventDataRow>());
            }
        }
        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        public List<EventDataRow> get(EventType e) {
            return storage.get(e);
        }
    }
}
