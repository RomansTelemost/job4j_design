package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean isNewElement = false;
        if (!contains(value)) {
            set.add(value);
            isNewElement = true;
        }
        return isNewElement;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        for (int i = 0; i < set.size(); i++) {
            if (Objects.equals(set.get(i), value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
