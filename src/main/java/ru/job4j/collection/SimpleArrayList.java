package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void grow() {
        container = Arrays.copyOf(container, Math.max(container.length, 10) * 2);
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T[] newContainer = Arrays.copyOf(container, container.length);
        T value = container[index];
        System.arraycopy(container, index + 1, newContainer, index, container.length - index - 1);
        newContainer[newContainer.length - 1] = null;
        container = newContainer;
        size--;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        int cacheModCount = modCount;

        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if (modCount != cacheModCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
