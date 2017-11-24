package com.javarush.task.task20.task2024;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable{
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) {

    }
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.write(node);
        for(Solution s : edges) outputStream.writeObject(s);
    }
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        node = inputStream.readInt();
        edges = new LinkedList<>();
        edges = (List<Solution>) inputStream.readObject();
    }
}
