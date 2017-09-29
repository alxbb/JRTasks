package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager sam = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (sam == null) sam = new StatisticAdvertisementManager();
        return sam;
    }

    public List<Advertisement> getActiveAd() {
        return storage
                .list()
                .stream()
                .filter(f -> f.getHits() > 0)
                .collect(Collectors.toList());
    }

    public List<Advertisement> getArchivedAd() {
        return storage
                .list()
                .stream()
                .filter(f -> f.getHits() == 0)
                .collect(Collectors.toList());
    }
}
