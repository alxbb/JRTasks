package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
//        Set<Long> set = new HashSet<>();
//        for (String s : strings) {
//            set.add(shortener.getId(s));
//        }
//        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
//        Set<String> set = new HashSet<>();
//        for (Long l : keys) {
//            set.add(shortener.getString(l));
//        }
//        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> startSet = new HashSet<String>((int) elementsNumber);
        for (int i = 0; i < elementsNumber; i++) {
            startSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> idsSet = getIds(shortener, startSet);
        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());

        start = new Date();
        Set<String> endSet = getStrings(shortener, idsSet);
        end = new Date();
        System.out.println(end.getTime() - start.getTime());

        if (startSet.size() != endSet.size()) {
            Helper.printMessage("Тест не пройден.");
        } else {
            boolean testFail = false;
            for (String s : startSet) {
                if (!endSet.contains(s)) {
                    testFail = true;
                    break;
                }
            }
            if (!testFail) Helper.printMessage("Тест пройден.");
            else Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println(System.getenv("temp"));
        Solution solution = new Solution();
        testStrategy(new HashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
        testStrategy(new HashBiMapStorageStrategy(), 10000L);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000L);
        testStrategy(new FileStorageStrategy(), 100L);



//        FileBucket fileBucket = new FileBucket();
//        fileBucket.putEntry(new Entry(100, 1000L, "10000", null));
//        Thread.sleep(10000);
    }
}
