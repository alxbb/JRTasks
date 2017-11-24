package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Cat {
    public String name;
    public int age;
    public int weight;

    public Cat() {
    }
}
