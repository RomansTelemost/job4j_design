package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) throws IOException {
        File dir = new File("src/main/java/ru/job4j/io/files");
        System.out.println(dir.exists());
        dir.mkdir();
        File file = new File("src/main/java/ru/job4j/io/files/file.txt");
        System.out.println(file.exists());
        file.createNewFile();
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.length());
        File newFile = new File("src/main/java/ru/job4j/io/files/newFile.txt");
        System.out.println("Renamed : " + file.renameTo(newFile));
    }
}
