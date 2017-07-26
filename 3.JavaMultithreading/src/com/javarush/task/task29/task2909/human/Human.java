package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private List<Human> children = new ArrayList<>();
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

//    protected int[] size;

    protected Size size;

    public class Size{
        public int height;
        public int weight;
    }

    private BloodGroup bloodGroup;

    public void addChild(Human human){
        if(human != null) children.add(human);
    }
    public void removeChild(Human human){
        if(human != null) children.remove(human);
    }

    public List<Human> getChildren() { return Collections.unmodifiableList(children);}


    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Human() { }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public Human(boolean isSoldier) {
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.height);
    }

    @Override
    public void live() {

    }

    public String getPosition(){return "Человек";}

    public void printData(){
        System.out.println(getPosition() + ": " + name);
    }

}