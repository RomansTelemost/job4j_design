package ru.job4j.serialization.json;

import com.google.gson.*;
import ru.job4j.generics.Person;

import java.lang.reflect.Type;

public class PersonSerializer implements JsonSerializer<Person> {

    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("name", person.getName());
        result.addProperty("age", person.getAge());
        result.addProperty("birthday", person.getBirthday().toString());

        JsonArray statuses = new JsonArray();
        result.add("statuses", statuses);
        for (String s : person.getStatuses()) {
            statuses.add(s);
        }

        return result;
    }
}
