package ru.job4j.ood.srp.report.task.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
