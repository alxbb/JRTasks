package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) return;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fname = br.readLine();
        br.close();
        StringBuffer sbuf = new StringBuffer();
        List<Map.Entry<Integer,Integer>> tList = new ArrayList<>();
        Map<Integer, Integer> result = new TreeMap<>();

        BufferedReader fr = new BufferedReader(new FileReader(fname));
        Map<Integer, Integer> map = new TreeMap<>();

        while(fr.ready()){
            sbuf.append(fr.readLine());
        }
        fr.close();

        String sTag = "<" + args[0].toLowerCase();
        String eTag = "</" + args[0].toLowerCase() + ">";
        String in = sbuf.toString().toLowerCase();

        int sPosition = 0;
        while (true){
            sPosition = in.indexOf(sTag,sPosition);
            if(sPosition == -1) break;
            map.put(sPosition + 1, 0);
            sPosition++;
        }
        sPosition = 0;
        while (true){
            sPosition = in.indexOf(eTag,sPosition);
            if(sPosition == -1) break;
            map.put(sPosition + eTag.length(), 1);
            sPosition++;
        }
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            tList.add(pair);
        }

        int i = 0;
        while(tList.size() > 0){
            if(tList.get(i).getValue() == 0 && tList.get(i+1).getValue() == 1){
                result.put(tList.get(i).getKey(), tList.get(i+1).getKey());
                tList.remove(i+1);
                tList.remove(i);
            }
            i++;
            if(i >= tList.size()) i = 0;
        }

        for(Map.Entry<Integer, Integer> pair : result.entrySet()){
            System.out.println(sbuf.substring(pair.getKey()-1,pair.getValue()));
        }

    }
}
