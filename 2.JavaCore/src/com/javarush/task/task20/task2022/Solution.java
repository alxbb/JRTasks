package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "c:\\temp\\java\\javaaa.txt";
        Solution solution = new Solution(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(solution);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Solution solution1 = (Solution) ois.readObject();
        System.out.println(solution);
        System.out.println(solution1);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "stream=" + stream +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
