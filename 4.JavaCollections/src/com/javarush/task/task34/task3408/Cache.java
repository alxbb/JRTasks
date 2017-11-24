package com.javarush.task.task34.task3408;

import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V value;
        if((value = cache.get(key))==null) cache.put(key, value = (V) clazz.getClass().getConstructor((Class<K>) key));
        return value;
    }

    public boolean put(V obj) {
        //TODO add your code here
        URL url = obj.getClass().getClassLoader().getResource("getKey");

        return cache.put((K) url, obj)!= null? true : false;
    }

    public int size() {
        return cache.size();
    }
}
