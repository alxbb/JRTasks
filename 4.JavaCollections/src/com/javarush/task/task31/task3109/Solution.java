package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        Properties properties = solution.getProperties("D:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3109\\properties.xml");
        properties.list(System.out);

//        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties = solution.getProperties("D:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3109\\properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("D:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3109\\notexist");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try(InputStream is = new FileInputStream(fileName)) {
            if (fileName.toLowerCase().endsWith(".xml"))
                properties.loadFromXML(is);
            else
                properties.load(is);
        } catch (FileNotFoundException e) {
            return properties;
        } catch (IOException e) {
            return properties;
        }

        return properties;
    }
}
//D:\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task31\task3109\properties.txt
//D:\JavaRushTasks\                  src\com\javarush\task\task31\task3109\properties.txt false
//D:\JavaRushTasks\                  src\com\javarush\task\task31\task3109\properties.xml false