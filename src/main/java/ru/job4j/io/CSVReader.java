package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        Map<String, Integer> tableColumnsName = new HashMap<>();
        List<String> rowsData = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            if (scanner.hasNext()) {
                String columnsRow = scanner.nextLine();
                String[] columns = columnsRow.split(delimiter);
                for (int i = 0; i < columns.length; i++) {
                    tableColumnsName.put(columns[i], i);
                }
            }
            while (scanner.hasNext()) {
                rowsData.add(scanner.nextLine());
            }
        }
        StringBuilder stringBuilder = filterData(rowsData,
                delimiter,
                argsName.get("filter").split(","),
                tableColumnsName);
        outResult(stringBuilder, argsName.get("out"));
    }

    private static StringBuilder filterData(List<String> rowsData,
                            String delimiter,
                            String[] filterColumnsName,
                            Map<String, Integer> tableColumnsName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.join(delimiter, filterColumnsName));
        for (String rowData : rowsData) {
            String[] dataArr = rowData.split(delimiter);
            StringJoiner joiner = new StringJoiner(delimiter);
            stringBuilder.append(System.lineSeparator());
            for (String filterColumnName : filterColumnsName) {
                joiner.add(dataArr[tableColumnsName.get(filterColumnName)]);
            }
            stringBuilder.append(joiner);
        }
        return stringBuilder;
    }

    private static void outResult(StringBuilder stringBuilder, String out) {
        if ("stdout".equalsIgnoreCase(out)) {
            System.out.println(stringBuilder);
        } else {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(out))) {
                printWriter.println(stringBuilder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateIncomingParameters(argsName);
        handle(argsName);
    }

    private static void validateIncomingParameters(ArgsName argsName) {
        if (!Path.of(argsName.get("path")).toFile().getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Path parameter is not correct!");
        }
        if (!(argsName.get("delimiter").equalsIgnoreCase(";")
                || argsName.get("delimiter").equalsIgnoreCase(","))) {
            throw new IllegalArgumentException("Delimiter parameter is not correct!");
        }
        if (argsName.get("out").length() == 0) {
            throw new IllegalArgumentException("Out parameter is not correct!");
        }
        if (argsName.get("filter").length() == 0) {
            throw new IllegalArgumentException("Filter parameter is not correct!");
        }
    }
}
