package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class ScannerExample1 {

    public static void main(String[] args) throws IOException {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 A 2",
                "3 4 B",
                "C 5 6"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            } else {
                scanner.next();
            }
        }
        System.out.println();
        data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        System.out.println();
        data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner2 = new Scanner(file).useRadix(16)) {
            while (scanner2.hasNextInt()) {
                System.out.print(scanner2.nextInt());
                System.out.print(" ");
            }
        }
    }
}
