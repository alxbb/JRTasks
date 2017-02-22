package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fname;
        fname = br.readLine();
        br.close();
        FileInputStream fis = new FileInputStream(fname);
        load(fis);
        fis.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties props = new Properties();
        for (Map.Entry<String,String> pair : properties.entrySet()){
            props.put(pair.getKey(), pair.getValue());
        }
        props.store(outputStream,"");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties props = new Properties();
        props.load(inputStream);
        properties.putAll((Map)props);
    }

    public static void main(String[] args) {


    }
}
