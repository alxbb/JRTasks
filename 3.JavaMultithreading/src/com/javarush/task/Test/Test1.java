package com.javarush.task.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test1 {
    private static Path zipFile = Paths.get("C:\\TEMP\\Java\\!java.bat");

    public static void main(String[] args) {
        String fName = zipFile.getFileName().toString();
        System.out.println(fName);
    }

}
