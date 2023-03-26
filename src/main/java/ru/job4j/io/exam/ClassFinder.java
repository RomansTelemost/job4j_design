package ru.job4j.io.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ClassFinder {

    private static final Logger LOG = LoggerFactory.getLogger(ClassFinder.class.getName());

    public static void main(String[] args) {
        try {
            ArgsName argsName = ArgsName.of(args);
            validateIncomingParameters(argsName);

            Path directory = Paths.get(argsName.get("d"));
            String searchType = argsName.get("t");
            String mask = argsName.get("n");

            Predicate<Path> condition = file -> true;
            if (ParameterType.MASK.name().equalsIgnoreCase(searchType)) {
                String regularExpressionMask = mask.replace(".", "\\.")
                        .replace("*", ".*")
                        .replace("?", ".")
                        .concat("$");
                condition = file -> Pattern.compile(regularExpressionMask).matcher(file.toFile().getName()).find();
            } else if (ParameterType.NAME.name().equalsIgnoreCase(searchType)) {
                condition = file -> file.toFile().getName().equalsIgnoreCase(mask);
            } else if (ParameterType.REGULAR.name().equalsIgnoreCase(searchType)) {
                condition = file -> Pattern.compile(mask).matcher(file.toFile().getName()).find();
            }
            List<Path> resultPathList = Search.search(directory, condition);
            try (PrintWriter printWriter = new PrintWriter(argsName.get("o"))) {
                resultPathList.forEach(printWriter::println);
            }
        } catch (IllegalArgumentException | IOException e) {
            LOG.error(String.format("Parameters %s", Arrays.toString(args)), e);
        }
    }

    private static void validateIncomingParameters(ArgsName argsName) {
        if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Parameter 'd' must contain reference to directory!");
        }
        if (!(ParameterType.MASK.name().equalsIgnoreCase(argsName.get("t"))
                || ParameterType.NAME.name().equalsIgnoreCase(argsName.get("t"))
                || ParameterType.REGULAR.name().equalsIgnoreCase(argsName.get("t")))) {
            throw new IllegalArgumentException("Parameter 't' must contain 'mask' or 'name' or 'regular' value!");
        }
        if (!Path.of(argsName.get("o")).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'o' must contain reference to result log file!");
        }
    }
}
