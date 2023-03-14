package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args).forEach(string -> {
            String[] pair = string.split("=", 2);
            values.put(pair[0].substring(1), pair[1]);
        });
    }

    private static void checkArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Optional<String> optionalStringWithDash = Arrays.stream(args).filter(
                string -> !string.startsWith("-")).findFirst();
        if (optionalStringWithDash.isPresent()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", optionalStringWithDash.get()));
        }
        Optional<String> optionalStringWithEquals = Arrays.stream(args).filter(
                string -> !string.contains("=")).findFirst();
        if (optionalStringWithEquals.isPresent()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", optionalStringWithEquals.get()));
        }
        Arrays.stream(args).forEach(string -> {
            String[] pair = string.split("=", 2);
            if (pair[0].length() == 1) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", string));
            }
            if (pair[1].length() == 0) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", string));
            }
        });
    }

    public static ArgsName of(String[] args) {
        checkArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
