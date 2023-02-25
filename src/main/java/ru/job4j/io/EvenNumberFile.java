package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("data/even.txt")) {
            int val;
            StringBuilder text = new StringBuilder();
            while ((val = fileInputStream.read()) != -1) {
                text.append((char) val);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                System.out.printf("Number %d is %s" + System.lineSeparator(), number, number % 2 == 0 ? "even" : "odd");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
