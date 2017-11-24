package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> toShow;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().size() == 0 || storage.list().isEmpty() || storage.list() == null) throw new NoVideoAvailableException();
        List<Advertisement> videos = storage.list();
        sortList(videos);
//        ConsoleHelper.writeMessage("=======================VIDEOS========================");
//        printList(videos);
        this.toShow = new ArrayList<>();
//        ConsoleHelper.writeMessage("=======================TOSHOW========================");
//        ConsoleHelper.writeMessage("timeSeconds = " + timeSeconds);
        createPlaySet();
        sortList(toShow);
        printList(toShow);
        toShow.stream().forEach(Advertisement::revalidate);
        try {
            Long amount = toShow.stream().map(a -> a.getAmountPerOneDisplaying()).reduce(Long::sum).get();

        StatisticManager
                .getInstance()
                .register(
                        new VideoSelectedEventDataRow(
                                toShow,
                                amount,
                                timeSeconds));
        } catch (NoSuchElementException e){
            throw new NoVideoAvailableException();
        }
    }

    private void sortList(List<Advertisement> toSort) {
        Collections.sort(toSort, (o1, o2) -> {
            int firstCompare = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            if (firstCompare == 0)
                return  o2.getDuration() - o1.getDuration();
            return firstCompare;
        });

    }

    private void createPlaySet() {
        List<Advertisement> matchedVideos = storage.list()
                .stream()
                .filter(x -> x.getDuration() <= timeSeconds)
                .filter(x -> x.getHits() > 0)
                .collect(Collectors.toList());

        sortList(matchedVideos);

        int totalDuration = 0;
        for (Advertisement ad : matchedVideos) {
            if ((totalDuration + ad.getDuration()) > timeSeconds) continue;
            toShow.add(ad);
            totalDuration += ad.getDuration();
        }
    }

    private void printList(List<Advertisement> toPrint) {
        toPrint.stream()
                .forEach(ad -> ConsoleHelper.writeMessage(ad.toString()));
    }
}
