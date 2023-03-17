package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String userQuestion = "";
            boolean hasRespond = true;
            while (!userQuestion.equalsIgnoreCase(OUT)) {
                userQuestion = scanner.nextLine();
                log.add(String.format("User asking : %s", userQuestion));
                if (userQuestion.equalsIgnoreCase(OUT)) {
                    saveLog(log);
                    continue;
                }
                if (userQuestion.equalsIgnoreCase(STOP)) {
                    hasRespond = false;
                    continue;
                }
                if (userQuestion.equalsIgnoreCase(CONTINUE)) {
                    hasRespond = true;
                    continue;
                }
                if (!hasRespond) {
                    continue;
                }
                int randomIndex = (int) Math.ceil(Math.random() * phrases.size());
                String botAnswer = phrases.get(randomIndex - 1);
                log.add(String.format("Bot answering : %s", botAnswer));
                System.out.println(botAnswer);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            while (bufferedReader.ready()) {
                phrases.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8, false)) {
            for (String string : log) {
                fileWriter.write(string);
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logConsoleChat.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
