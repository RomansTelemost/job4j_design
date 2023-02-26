package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        /**
        try (FileInputStream in = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        try (BufferedReader in = new BufferedReader(new FileReader("data/input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
