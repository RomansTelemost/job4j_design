package ru.job4j.ood.srp.report.task.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.report.task.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.task.model.Employee;
import ru.job4j.ood.srp.report.task.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJsonFormatEngine extends ReportEngine {

    private final Gson gson;

    public ReportJsonFormatEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
        gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
