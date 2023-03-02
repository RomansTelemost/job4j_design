package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    @Test
    public void testServerLogAnalysisExample1(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(stringBuilder::append);
        }
        String result = "10:57:01;10:59:01;"
                + "11:01:02;11:02:02;";

        assertThat(stringBuilder.toString()).isEqualTo(result);
    }

    @Test
    public void testServerLogAnalysisExample2(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(stringBuilder::append);
        }
        String result = "10:57:01;11:02:02;";

        assertThat(stringBuilder.toString()).isEqualTo(result);
    }

    @Test
    public void whenFileHasNotCorrectFormatThenException() throws IOException {
        Analysis analysis = new Analysis();
        String targetFile = "data/targetExample3.csv";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            analysis.unavailable("data/serverExample3.log", targetFile);
        });

        assertThat(exception.getMessage()).isEqualTo("Can't find status or code in row : 200");
    }
}