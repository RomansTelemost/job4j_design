package ru.job4j.ood.srp.report.task.report;

import ru.job4j.ood.srp.report.task.formatter.DateTimeParser;
import ru.job4j.ood.srp.report.task.model.Employee;
import ru.job4j.ood.srp.report.task.model.Store;
import ru.job4j.ood.srp.report.task.model.format.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXmlFormatEngine extends ReportEngine {

    private final Marshaller marshaller;

    public ReportXmlFormatEngine(Store store, DateTimeParser<Calendar> dateTimeParser) throws JAXBException {
        super(store, dateTimeParser);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
