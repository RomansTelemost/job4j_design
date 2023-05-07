package ru.job4j.ood.srp.reportTask.report;

import ru.job4j.ood.srp.reportTask.currency.Currency;
import ru.job4j.ood.srp.reportTask.currency.CurrencyConverter;
import ru.job4j.ood.srp.reportTask.formatter.DateTimeParser;
import ru.job4j.ood.srp.reportTask.model.Employee;
import ru.job4j.ood.srp.reportTask.model.Store;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccountEngine extends ReportEngine {

    private final CurrencyConverter currencyConverter;

    private final Currency convertToCurrency;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");

    public ReportAccountEngine(Store store,
                               DateTimeParser<Calendar> dateTimeParser,
                               CurrencyConverter currencyConverter, Currency convertToCurrency) {
        super(store, dateTimeParser);
        this.currencyConverter = currencyConverter;
        this.convertToCurrency = convertToCurrency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(DECIMAL_FORMAT.format(currencyConverter.convert(Currency.RUB, employee.getSalary(), convertToCurrency)))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
