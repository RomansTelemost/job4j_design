package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {

    private List<Session> sessionList = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessionList.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row < 0
                || column < 0) {
            throw new IllegalArgumentException("Row or column cannot less then 1");
        }
        return new Ticket3D(date);
    }

    @Override
    public void add(Session session) {
        sessionList.add(session);
    }
}
