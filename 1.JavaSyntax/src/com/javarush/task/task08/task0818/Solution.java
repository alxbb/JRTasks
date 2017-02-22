package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Ivanov 0", 0);
        map.put("Ivanov 1", 100);
        map.put("Ivanov 2", 200);
        map.put("Ivanov 3", 300);
        map.put("Ivanov 4", 400);
        map.put("Ivanov 5", 500);
        map.put("Ivanov 6", 600);
        map.put("Ivanov 7", 700);
        map.put("Ivanov 8", 800);
        map.put("Ivanov 9", 900);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> pair = it.next();
            if(pair.getValue() < 500) it.remove();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
    }
}