package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        if (args.length < 2) return;
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        File path = new File(args[0]);
        resultFileAbsolutePath = allFilesContent;

        try{
            FileOutputStream fos = new FileOutputStream(allFilesContent, true);
            fos.close();

            List<File> list = getDirectoryTree(path);

            list.stream()
                    .filter(f -> f.isFile())
                    .filter(f -> f.length() > 50)
                    .forEach(f -> FileUtils.deleteFile(f));

            fos = new FileOutputStream(resultFileAbsolutePath, true);
            FileOutputStream finalFos = fos;
            list.stream()
                    .filter(f -> f.isFile())
                    .filter(f -> f.length() <= 50)
                    .filter(f -> f.getName().toLowerCase().endsWith(".txt"))
                    .sorted((f1, f2) -> f1.getName().compareTo(f2.getName()))
                    .forEach(file -> {
                                try (FileInputStream fis = new FileInputStream(file)) {
                                    byte[] bytes = new byte[(int) file.length()];
                                    fis.read(bytes);
                                    finalFos.write(bytes);
                                    finalFos.write("\n".getBytes());
                                    finalFos.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<File> getDirectoryTree(File start) {
        List<File> list = new ArrayList<>();
        File[] files = start.listFiles();
        Collections.addAll(list, files);
        File current;

        for (int i = 0; i < files.length; i++) {
            current = files[i];
            if (current.isDirectory()) list.addAll(getDirectoryTree(current));
        }
        return list;
    }
}
