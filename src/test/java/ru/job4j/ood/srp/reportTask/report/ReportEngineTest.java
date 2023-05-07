package ru.job4j.ood.srp.reportTask.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reportTask.currency.Currency;
import ru.job4j.ood.srp.reportTask.currency.CurrencyConverter;
import ru.job4j.ood.srp.reportTask.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.reportTask.formatter.DateTimeParser;
import ru.job4j.ood.srp.reportTask.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.reportTask.model.Employee;
import ru.job4j.ood.srp.reportTask.model.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy HH:mm"));
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy HH:mm"));
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        store.add(worker);
        Report engine = new ReportAccountEngine(store, parser, currencyConverter, Currency.USD);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append("1.62")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker100 = new Employee("Ivan100", now, now, 100);
        Employee worker200 = new Employee("Ivan200", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy HH:mm"));
        store.add(worker100);
        store.add(worker200);
        Report engine = new ReportHrEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker200.getName()).append(" ")
                .append(worker200.getSalary())
                .append(System.lineSeparator())
                .append(worker100.getName()).append(" ")
                .append(worker100.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenITGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker100 = new Employee("Ivan100", now, now, 100);
        Employee worker200 = new Employee("Ivan200", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy"));
        store.add(worker100);
        store.add(worker200);
        Report engine = new ReportItEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker100.getName()).append("; ")
                .append(parser.parse(worker100.getHired())).append("; ")
                .append(parser.parse(worker100.getFired())).append("; ")
                .append(worker100.getSalary())
                .append(System.lineSeparator())
                .append(worker200.getName()).append("; ")
                .append(parser.parse(worker200.getHired())).append("; ")
                .append(parser.parse(worker200.getFired())).append("; ")
                .append(worker200.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}