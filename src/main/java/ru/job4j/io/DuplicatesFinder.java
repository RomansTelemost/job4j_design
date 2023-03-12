package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects"), duplicatesVisitor);
        printDuplicatesData(duplicatesVisitor.doublePaths);
    }

    private static void printDuplicatesData(Map<FileProperty, List<Path>> filesWithPathsMap) {
        filesWithPathsMap.entrySet().stream().filter((k) -> k.getValue().size() > 1).forEach(e -> {
            System.out.println(e.getKey());
            e.getValue().forEach(System.out::println);
        });
    }
}
