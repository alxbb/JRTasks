package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
    public void setPartOfName   (String partOfName) {
        this.partOfName = partOfName;
    }
    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }
    public void setMinSize      (int minSize) {
        this.minSize = minSize;
    }
    public void setMaxSize      (int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = attrs.size();
//        System.out.println(file + " " + size );
        boolean next = false;

        if(matchSize(size) && nameContains(file,partOfName) && fileContains(file, partOfContent)) foundFiles.add(file);

        return super.visitFile(file, attrs);
    }

    private boolean matchSize (long size){
        if(maxSize > 0 && size < maxSize && size > minSize) return true;
        if(maxSize == 0 && size > minSize)return true;
        return false;
    }

    private boolean nameContains (Path file, String toFind){
        if(toFind == null) return true;
        return file.getFileName().toString().contains(toFind);
    }

    private boolean fileContains(Path file, String toFind){
        if(toFind == null) return true;
        boolean bOut = false;
        try {
            bOut = Files.readAllLines(file).stream().anyMatch(s->s.contains(toFind));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bOut;
    }
}
