package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), duplicatesVisitor);
        duplicatesVisitor.doublePaths.entrySet().stream().filter((k) -> k.getValue().size() > 1).forEach(e -> {
            System.out.println(e.getKey());
            e.getValue().forEach(System.out::println);
        });
    }
}
