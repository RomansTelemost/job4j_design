package ru.job4j.ood.tdd;

import java.util.Date;
import java.util.Objects;

public class Ticket3D implements Ticket {

    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return Objects.equals(date, ticket3D.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
