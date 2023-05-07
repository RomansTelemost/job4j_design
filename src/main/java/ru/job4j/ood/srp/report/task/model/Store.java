package ru.job4j.ood.srp.report.task.model;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
