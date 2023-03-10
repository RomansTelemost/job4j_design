package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.generics.Person;
import ru.job4j.serialization.java.Contact;

public class Main {

    public static void main(String[] args) {
        final Person person = new Person("Rowan", 30, new Contact(1, "11-111"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(person);
        System.out.println(s);
        System.out.println("***");

        Person p = gson.fromJson(s, Person.class);
        System.out.println(p);
        System.out.println("***");
        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":1,"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
