package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя путь\\имя архива: ");
        String zipFileName = br.readLine();
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(zipFileName));

        System.out.println("Введите путь\\имя архивируемого файла: ");
        String sourceFileName = br.readLine();
        zipFileManager.createZip(Paths.get(sourceFileName));

        br.close();
        new ExitCommand().execute();
    }
}
