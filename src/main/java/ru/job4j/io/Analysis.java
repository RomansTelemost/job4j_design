package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Analysis {

    private static final Logger LOG = LoggerFactory.getLogger(Analysis.class.getName());

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            String errorStartDate = "";
            while (reader.ready()) {
                String line = reader.readLine();
                String[] statusAndDate = line.split(" ", 2);
                if (statusAndDate.length != 2
                        || statusAndDate[0].isBlank()
                        || statusAndDate[1].isBlank()) {
                    throw new IllegalArgumentException("Can't find status or code in row : " + line);
                }
                if (("400".equals(statusAndDate[0])
                        || "500".equals(statusAndDate[0]))
                        && errorStartDate.isBlank()) {
                    errorStartDate = statusAndDate[1];
                }
                if (!("400".equals(statusAndDate[0])
                        || "500".equals(statusAndDate[0]))
                        && !errorStartDate.isBlank()) {
                    writer.write(errorStartDate);
                    writer.write(";");
                    writer.write(statusAndDate[1]);
                    writer.write(";");
                    writer.write(System.lineSeparator());
                    errorStartDate = "";
                }

            }
        } catch (IOException exception) {
            LOG.error("Error while read/write file", exception);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/serverExample1.log", "data/targetExample1.csv");
    }
}
