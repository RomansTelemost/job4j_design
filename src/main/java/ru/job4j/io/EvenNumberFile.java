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
            text.replace(0, text.length(), "");
            for (String line : lines) {
                int number = Integer.parseInt(line);
                StringBuilder evenOrOddText = new StringBuilder();
                evenOrOddText.append("Number ")
                        .append(number)
                        .append(" is ")
                        .append(number % 2 == 0 ? "even" : "odd")
                        .append(System.lineSeparator());
                System.out.print(evenOrOddText);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
