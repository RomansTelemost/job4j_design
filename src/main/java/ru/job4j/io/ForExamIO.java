package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.generics.Person;
import ru.job4j.io.objectstream.Car;
import ru.job4j.serialization.json.PersonDeserializer;
import ru.job4j.serialization.json.PersonSerializer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;

public class ForExamIO {

    private static final Logger LOG = LoggerFactory.getLogger(ForExamIO.class.getName());

    private static final String FILE_WITH_RUSSIAN_LETTERS = "data/botAnswers.txt";
    private static final String FILE_WITH_LATIN_LETTERS = "data/input.txt";

    //Считается что inputStream - FileInputStream блокирует программу когда вычитывает данные.
    public static void main(String[] args) throws IOException {
        /**
         * input();
         * output();
         *         file();
         *         pathPathsFiles();
         */
        gson();
    }

    private static void input() throws IOException {
        /**
         * Абстракция InputStream - methods: read, available, close
         *
         * Основные реализации:
         * 1- ByteArrayInputStream - принимает массив байтов. Чатает каждый поштучно и выводит. Метод read synchronized
         * 2- FileInputStream - Принимает файл и читает побайтово данные. Конструктор передаем строку, файл, файлДескриптор
         * 2.1- InputStreamReader - Наследуется от Reader. Обертка над InputStream. Можно задать кодировку через конструктор. Есть метод ready
         * 3- ObjectInputStream -
         */

        //1
        String a = "JavaOne\n";
        readByteArrayInputStream(a.getBytes());
        //1.1
        byte[] bytes = new byte[]{'J', 'a', 'v', 'a', 'T', 'w', 'o', '\n'};
        readByteArrayInputStream(bytes);

        //2
        readFileInputStream(FILE_WITH_LATIN_LETTERS);
        //2.1
        readInputStreamReader(new FileInputStream(FILE_WITH_RUSSIAN_LETTERS));

    }

    private static void readByteArrayInputStream(byte[] bytes) {
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            int i;
            while ((i = inputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            LOG.error("Reading data " + Arrays.toString(bytes), e);
        }
    }

    private static void readFileInputStream(String fileName) {
        try (InputStream inputStream = new FileInputStream(fileName)) {
            int i;
            while ((i = inputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            LOG.error(String.format("File name %s", fileName), e);
        }
    }

    /**
     * Из названия понятно что InputStreamReader читает InputStream :)
     * Можно задать кодировку для правильного чтения файлов.
     *
     * @param inputStream
     */
    private static void readInputStreamReader(InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"))) {
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            LOG.error("inputStream ", e);
        }
    }

    private static void output() {

        /**
         *
         * Основные реализации:
         * 3- ObjectOutputStream -
         * 4- PipedOutputStream -
         */

        /**
         * 3
         */
        writeObjectOutputStream();
    }

    private static void writeObjectOutputStream() {
        Car car1 = new Car("Фирма", "Модель", 2000);
        Car car2 = new Car("Фирма Baj", "Модель F", 2010);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/objectstream/serialized.dat"))) {
            out.writeObject(car1);
            out.writeObject(car2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/objectstream/serialized.dat"))) {
            Car deserialized1 = (Car) in.readObject();
            Car deserialized2 = (Car) in.readObject();
            System.out.println(deserialized1.toString());
            System.out.println(deserialized2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void file() {
        /**
         * Будет полностью перемещен
         */
        File file = new File("data/fileOriginal.txt");
        file.renameTo(new File("newDir/dir1/fileCopyOfOriginal.txt"));
    }

    private static void pathPathsFiles() throws IOException {
        /**
         * Перемещение в nio. Удаляет старый
         * Так же можно переименовать
         */

        Path p = Paths.get("data/fileOriginal.txt");
        Files.copy(p, Path.of("newDir/dir1/fileCopyOfOriginal.txt"), StandardCopyOption.REPLACE_EXISTING);
        /**
         * Files.move(p, Path.of("newDir/dir1/fileCopyOfOriginal.txt"));
         */
    }

    private static void gson() {
        Person p = new Person("Roman", 30, new Date());

        String[] statuses = new String[]{"status1", "status2"};
        p.setStatuses(statuses);
        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Person.class, new PersonSerializer())
                .create();
        String jsonPerson = gson.toJson(p);
        System.out.println(jsonPerson);
        System.out.println("***");

        final Gson gsonDe = new GsonBuilder()
                .registerTypeAdapter(Person.class, new PersonDeserializer())
                .create();
        Person re = gsonDe.fromJson(jsonPerson, Person.class);
        System.out.println(re);
    }
}
