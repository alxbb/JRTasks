package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) throws IOException {

//        FileProperties fp1 = new FileProperties("c:/fakefile1",10000, 1000, 1);
//        FileProperties fp2 = new FileProperties("c:/fakefile2",0, 0, 1);
//        System.out.println(fp1);
//        System.out.println(fp2);
//        FileManager fm = new FileManager(Paths.get("C:\\TEMP\\forms60\\"));
//        for(Path p : fm.getFileList()) System.out.println(p.toString());

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);
    }


    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив"      , Operation.CREATE .ordinal () ));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив"        , Operation.ADD    .ordinal () ));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива"       , Operation.REMOVE .ordinal () ));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив"            , Operation.EXTRACT.ordinal () ));
        ConsoleHelper.writeMessage(String.format("\t %d - просмотреть содержимое архива", Operation.CONTENT.ordinal () ));
        ConsoleHelper.writeMessage(String.format("\t %d - выход"                        , Operation.EXIT   .ordinal () ));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}