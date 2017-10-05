package com.javarush.task.task31.task3105;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) return;
        Path file = Paths.get(args[0]);
        Path zip  = Paths.get(args[1]);
        Map<ZipEntry, byte[]> map = new HashMap<>();
        ZipEntry entry = null;
        byte[] buf = null;
        FileOutputStream fos = new FileOutputStream(zip.toString());

        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(zip.toString()))){
            while((entry = zis.getNextEntry())!= null){
                buf = new byte[(int) entry.getSize()];
                zis.read(buf);
                map.put(entry,buf);
            }
            zis.close();
        }
        Files.delete(zip);

        try(ZipOutputStream zos = new ZipOutputStream(fos)){
            map.forEach((e,d)->{
                try {
                    zos.putNextEntry(e);
                    zos.write(d);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            entry = new ZipEntry("new/" + file.getFileName());
            zos.putNextEntry(entry);
            Files.copy(file,zos);

            zos.flush();
            zos.close();
            fos.flush();
            fos.close();
        }
    }
}
