package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null,null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry() {
        if (this.getFileSize() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
                Entry entry = (Entry) inputStream.readObject();
                return entry;
            } catch (IOException e) {
            e.printStackTrace();
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
