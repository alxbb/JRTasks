package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener){
        String s1 = "Test string1";
        String s2 = "Test string2";
        String s3 = "Test string1";
        Long l1 = shortener.getId(s1);
        Long l2 = shortener.getId(s2);
        Long l3 = shortener.getId(s3);

        Assert.assertNotEquals(l1,l2);
        Assert.assertEquals(l1,l3);

        String s11 = shortener.getString(l1);
        String s21 = shortener.getString(l2);
        String s31 = shortener.getString(l3);

        Assert.assertEquals(s1,s11);
        Assert.assertEquals(s2,s21);
        Assert.assertEquals(s3,s31);
    }

    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy ss = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy ss = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy ss = new FileStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy ss = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy ss = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy ss = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ss);
        testStorage(shortener);
    }
}
