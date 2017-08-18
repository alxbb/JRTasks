package com.javarush.task.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test3 {
    static Path p1 = Paths.get("\\temp\\file.txt");
    static Path p2 = Paths.get("temp\\file.txt");

    public static void main(String[] args) {
        System.out.println(p1.getFileName());
        System.out.println(p2.getFileName());
        System.out.println(p1.getFileName().equals(p2.getFileName()));
    }

}
