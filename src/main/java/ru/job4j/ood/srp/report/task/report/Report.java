package ru.job4j.ood.srp.report.task.report;

import ru.job4j.ood.srp.report.task.model.Employee;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);
}
