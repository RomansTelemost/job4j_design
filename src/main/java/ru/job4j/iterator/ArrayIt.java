package ru.job4j.iterator;

import java.util.Iterator;

public class ArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        return data[point++];
    }
}
