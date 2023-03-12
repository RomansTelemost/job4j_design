package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> doublePaths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());

        if (doublePaths.containsKey(fileProperty)) {
            List<Path> list = doublePaths.get(fileProperty);
            list.add(file.toAbsolutePath());
        } else {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            doublePaths.put(fileProperty, list);
        }
        return super.visitFile(file, attrs);
    }
}
