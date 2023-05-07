package ru.job4j.ood.srp.report.task.formatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportDateTimeParser implements DateTimeParser<Calendar> {

    private final SimpleDateFormat dateFormat;

    public ReportDateTimeParser(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String parse(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }
}
