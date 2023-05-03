package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaxMinTest {

    @Test
    public void whenArrayContainsMaxLengthString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = new ArrayList<>();
        list.add("42");
        list.add("String");
        list.add("2");
        String expect = "String";
        assertThat(maxMin.max(list, Comparator.comparingInt(String::length))).isEqualTo(expect);
    }

    @Test
    public void whenArrayContainsMinLengthString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = new ArrayList<>();
        list.add("42");
        list.add("String");
        list.add("2");
        String expect = "2";
        assertThat(maxMin.min(list, Comparator.comparingInt(String::length))).isEqualTo(expect);
    }

}