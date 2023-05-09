package ru.job4j.ood.srp.report.task.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.report.task.currency.Currency;
import ru.job4j.ood.srp.report.task.currency.CurrencyConverter;
import ru.job4j.ood.srp.report.task.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.report.task.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.task.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.report.task.model.Employee;
import ru.job4j.ood.srp.report.task.model.MemStore;

import javax.xml.bind.JAXBException;
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

    @Test
    public void whenJsonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan100", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy"));
        store.add(worker);
        Report engine = new ReportJsonFormatEngine(store, parser);

        StringBuilder expect = new StringBuilder()
                .append("[").append("{")
                .append("\"name\":\"").append(worker.getName()).append("\",")
                .append("\"hired\":{")
                .append("\"year\":").append(worker.getHired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker.getHired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getHired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getHired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getHired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker.getHired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"fired\":{").append("\"year\":").append(worker.getFired()
                        .get(Calendar.YEAR)).append(",")
                .append("\"month\":").append(worker.getFired()
                        .get(Calendar.MONTH)).append(",")
                .append("\"dayOfMonth\":").append(worker.getFired()
                        .get(Calendar.DAY_OF_MONTH)).append(",")
                .append("\"hourOfDay\":").append(worker.getFired()
                        .get(Calendar.HOUR_OF_DAY)).append(",")
                .append("\"minute\":").append(worker.getFired()
                        .get(Calendar.MINUTE)).append(",")
                .append("\"second\":").append(worker.getFired()
                        .get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(worker.getSalary())
                .append("}").append("]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan100", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser(new SimpleDateFormat("dd:MM:yyyy"));
        store.add(worker);
        Report engine = new ReportXmlFormatEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <fired>").append(format.format(worker.getHired().getTime())).append("</fired>\n")
                .append("        <hired>").append(format.format(worker.getFired().getTime())).append("</hired>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}