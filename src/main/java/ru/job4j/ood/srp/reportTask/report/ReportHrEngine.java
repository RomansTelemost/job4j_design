package ru.job4j.ood.srp.reportTask.report;

import ru.job4j.ood.srp.reportTask.formatter.DateTimeParser;
import ru.job4j.ood.srp.reportTask.model.Employee;
import ru.job4j.ood.srp.reportTask.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportHrEngine extends ReportEngine {

    public ReportHrEngine(Store store, DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter).stream().sorted((a, b) -> (int) (b.getSalary() - a.getSalary())).toList()) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
