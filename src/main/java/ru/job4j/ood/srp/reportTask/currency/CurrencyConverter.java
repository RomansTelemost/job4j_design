package ru.job4j.ood.srp.reportTask.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
