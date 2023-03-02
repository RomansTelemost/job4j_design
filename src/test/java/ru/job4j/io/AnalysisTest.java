package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    @Test
    public void testServerLogAnalysisExample1() throws IOException {
        Analysis analysis = new Analysis();
        String targetFile = "data/targetExample1.csv";
        analysis.unavailable("data/serverExample1.log", targetFile);

        BufferedReader in = new BufferedReader(new FileReader(targetFile));
        StringBuilder stringBuilder = new StringBuilder();
        while (in.ready()) {
            stringBuilder.append(in.readLine());
        }

        String result = "10:57:01;10:59:01;"
                + "11:01:02;11:02:02;";
        assertThat(stringBuilder.toString()).isEqualTo(result);
    }

    @Test
    public void testServerLogAnalysisExample2() throws IOException {
        Analysis analysis = new Analysis();
        String targetFile = "data/targetExample2.csv";
        analysis.unavailable("data/serverExample2.log", targetFile);

        BufferedReader in = new BufferedReader(new FileReader(targetFile));
        StringBuilder stringBuilder = new StringBuilder();
        while (in.ready()) {
            stringBuilder.append(in.readLine());
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