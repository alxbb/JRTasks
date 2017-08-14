package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {

        String sourceName = source.getFileName().toString();
        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
        ZipEntry zipEntry = new ZipEntry(sourceName);
        zipOutputStream.putNextEntry(zipEntry);

        try(InputStream ifs = Files.newInputStream(source)){
            while(ifs.available() > 0) {
                zipOutputStream.write(ifs.read());
            }
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }
}
