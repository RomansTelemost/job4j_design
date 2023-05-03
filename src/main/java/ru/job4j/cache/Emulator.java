package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    public static final Integer PUT_FILE_TO_CACHE = 1;
    public static final Integer GET_FILE_FROM_CACHE = 2;

    public static final String SELECT = "Выберите кэшируемую директорию";
    private static final String FILE_NAME = "Укажите имя файла";

    public static final String MENU = """                
                Введите 1, для загрузки содержимого файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        System.out.println(SELECT);
        Scanner scanner = new Scanner(System.in);
        AbstractCache<String, String> dirFileCache = new DirFileCache(scanner.nextLine());

        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (PUT_FILE_TO_CACHE == userChoice) {
                System.out.println(FILE_NAME);
                String fileName = scanner.nextLine();
                dirFileCache.put(fileName, dirFileCache.get(fileName));
            } else if (GET_FILE_FROM_CACHE == userChoice) {
                System.out.println(FILE_NAME);
                String fileName = scanner.nextLine();
                System.out.println(dirFileCache.get(fileName));
            } else {
                run = false;
            }
        }
    }
}
