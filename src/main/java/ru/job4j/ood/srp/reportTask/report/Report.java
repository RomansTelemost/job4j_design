package ru.job4j.ood.srp.reportTask.report;

import ru.job4j.ood.srp.reportTask.model.Employee;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);
}
