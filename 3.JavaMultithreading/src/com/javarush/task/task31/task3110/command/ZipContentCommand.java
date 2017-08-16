package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.*;

import java.util.List;

public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        for(FileProperties fp : zipFileManager.getFilesList()){
            ConsoleHelper.writeMessage(fp.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
