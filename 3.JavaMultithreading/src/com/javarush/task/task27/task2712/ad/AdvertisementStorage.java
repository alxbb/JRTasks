package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage storage = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video" , 5000, 100, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Русское Video2" , 5100, 15, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "First Video3" , 2500, 100, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Русское Video4" , 3100, 20, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Еусское Video5" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Ёусское Video6" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Жусское Video7" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "еусское Video8" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "жусское Video9" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "ёусское Video10" , 3100, 2, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "First Video5" , 4100, 100, 3 * 60 )); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100 , 10 , 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video" , 400 , 2  , 10 * 60)); //10 min
    }

    public static AdvertisementStorage getInstance(){
        if(storage == null) storage = new AdvertisementStorage();
        return storage;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
