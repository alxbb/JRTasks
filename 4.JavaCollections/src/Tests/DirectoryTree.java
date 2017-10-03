package Tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DirectoryTree {
    public static void main(String[] args) throws IOException {
        File start = new File("w:\\KASSA");
        long s = System.currentTimeMillis();
        List<File> tree;
        tree = getDirectoryTree(start);
        System.out.printf("Кол-во файлов : %d время рекурсия(мс) %d%n", tree.size(), System.currentTimeMillis() - s);
        s = System.currentTimeMillis();
        tree = getFileTree(start);
        System.out.printf("Кол-во файлов : %d время очередь(мс) %d%n", tree.size(), System.currentTimeMillis() - s);

//        tree.stream().filter(f->f.getName().toLowerCase().endsWith(".cab")).forEach(System.out::println);

    }

    public static List<File> getDirectoryTree(File start) {
//        System.out.println(start);
        List<File> list = new ArrayList<>();
        List<File> tmpList = new ArrayList<>();
        File[] files = start.listFiles();
        if (files == null) return list;

        Collections.addAll(list, files);
        File current;

        for (int i = 0; i < files.length; i++) {
            current = files[i];
            if (current.isDirectory()) {
                tmpList = getDirectoryTree(current);
                if (tmpList != null) list.addAll(tmpList);
                else list.add(current);
            }
        }
        return list;
    }

    public static List<File> getFileTree(File start) throws IOException {
        Queue<File> queue = new PriorityQueue<>();
        List<File> list = new ArrayList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            File[] files = queue.poll().listFiles();
            if(files == null) continue;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if(file.isDirectory()) {
                    list.add(file);
                    queue.add(file);
                } else list.add(file);
            }
        }
        return list;
    }
}
