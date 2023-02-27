package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            bufferedReader.lines().forEach(line -> {
                if (!line.isEmpty()
                    && !line.startsWith("#")) {
                    StringBuilder sb = new StringBuilder();
                    String[] keyAndValue = line.split("=", 2);
                    if (!keyAndValue[keyAndValue.length - 2].isBlank()
                            && !keyAndValue[keyAndValue.length - 1].isBlank()) {
                        String[] key = keyAndValue[0].split("\\.");
                        values.put(key[key.length - 1], keyAndValue[keyAndValue.length - 1]);
                    } else {
                        sb.append("In line \'").append(line).append("\'").append(" not found key or value!");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/pair_with_comment.properties");
        config.load();

        config.values.forEach((key, value) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Key: ").append(key).append(" - ").append("value: ").append(value);
            System.out.println(sb);
        });
    }
}
