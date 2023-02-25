package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    int number = i * j;

                    String separator = "";
                    if (number < 10 && j > 1) {
                        separator = " ";
                    }
                    out.write((separator.concat(number + "").concat(" ")).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
