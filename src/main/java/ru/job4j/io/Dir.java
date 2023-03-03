package ru.job4j.io;

import java.io.File;

public class Dir {

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        System.out.println(String.format("size : %s", file.getFreeSpace()));

        long sizeAmount = 0;
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                sizeAmount += walkFiles(file);
            } else {
                sizeAmount = file.length();
            }
            System.out.printf("File name: %s, size: %d", subfile.getName(), sizeAmount);
            System.out.println();
        }
    }

    public static long walkFiles(File file) {
        long sizeAmount = 0;
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                sizeAmount += walkFiles(subfile);
            }
            sizeAmount += file.length();
        }
        return sizeAmount;
    }
}
