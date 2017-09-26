package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

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

    public Map<Date, List<EventDataRow>> getAdvertisementProfit(){ /* TODO*/
        List<EventDataRow> list = statisticStorage.get(EventType.SELECTED_VIDEOS);

        Map<Date, List<EventDataRow>> retMap = list.stream().collect(Collectors.groupingBy(EventDataRow::getDate));


        return retMap;
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
