package ru.job4j.ood.srp.report.task.report.reportformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFormatter implements Formatter {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String convert(String inputString) {
        return gson.toJson(inputString);
    }
}
