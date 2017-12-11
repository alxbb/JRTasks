package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;
import com.javarush.task.task33.task3310.tests.FunctionalTest;
import com.javarush.task.task33.task3310.tests.SpeedTest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
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
//        testStrategy(new HashMapStorageStrategy(), 10000L);
//        testStrategy(new OurHashMapStorageStrategy(), 10000L);
//        testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
//        testStrategy(new HashBiMapStorageStrategy(), 10000L);
//        testStrategy(new DualHashBidiMapStorageStrategy(), 10000L);
//        testStrategy(new FileStorageStrategy(), 100L);
        FunctionalTest ft = new FunctionalTest();
        ft.testDualHashBidiMapStorageStrategy();
        ft.testFileStorageStrategy();
        ft.testHashBiMapStorageStrategy();
        ft.testHashMapStorageStrategy();
        ft.testOurHashBiMapStorageStrategy();
        ft.testOurHashMapStorageStrategy();

        SpeedTest st = new SpeedTest();
        st.testHashMapStorage();

//        FileBucket fileBucket = new FileBucket();
//        fileBucket.putEntry(new Entry(100, 1000L, "10000", null));
//        Thread.sleep(10000);
    }
}
