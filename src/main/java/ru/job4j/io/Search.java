package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validateIncomingParameters(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateIncomingParameters(String[] args) throws InvalidObjectException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not found 2 parameters. Root folder and extension.");
        }
        File file = Path.of(args[0]).toFile();
        if (!file.exists()
                || !file.isDirectory()) {
            throw new IllegalArgumentException("Path to root folder is invalid.");
        }
        if (args[1].isBlank()
                || !args[1].startsWith(".")
                || args[1].length() < 2) {
            throw new IllegalArgumentException("Extension data must starts with '.' and contains only letters");
        }
    }
}
