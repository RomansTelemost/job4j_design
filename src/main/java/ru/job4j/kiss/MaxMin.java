package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> T find(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T item : value) {
            if (comparator.compare(result, item) < 0) {
                result = item;
            }
        }
        return result;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }
}
