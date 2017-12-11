package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        if (shortener == null || strings == null || ids == null)
            throw new IllegalArgumentException();
        if (strings.size() == 0) return 0;
        Date start = new Date();
        for (String s : strings) ids.add(shortener.getId(s));
        Date end = new Date();

        return end.getTime() - start.getTime();
    }
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        if (shortener == null || strings == null || ids == null)
            throw new IllegalArgumentException();
        if (ids.size() == 0) return 0;
        Date start = new Date();
        for (Long l : ids) strings.add(shortener.getString(l));
        Date end = new Date();

        return end.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage(){
        int limit = 50000;
        StorageStrategy hmss = new HashMapStorageStrategy();
        StorageStrategy hbmss = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(hmss);
        Shortener shortener2 = new Shortener(hbmss);
        Set<String> origStrings = new HashSet<>(limit);
        Set<Long> ids = new HashSet<>(limit);

        for (int i = 0; i < limit; i++) { origStrings.add(Helper.generateRandomString()); }

        Long l1 = getTimeForGettingIds(shortener1,origStrings, ids);
        Long l2 = getTimeForGettingIds(shortener2,origStrings, ids);
//        System.out.println(l1 + " " +l2 + " " + (l1-l2));
        Assert.assertTrue(l1 > l2);

        Long l3 = getTimeForGettingStrings(shortener1,ids, origStrings);
        Long l4 = getTimeForGettingStrings(shortener2,ids, origStrings);
//        System.out.println(l3 + " " +l4 + " " + (l4-l3));
        Assert.assertEquals(l3, l4, 30);
    }
}
