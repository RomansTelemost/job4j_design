package ru.job4j.serialization.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class GalacticSerializer implements JsonSerializer<Galactic> {

    @Override
    public JsonElement serialize(Galactic galactic, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
