package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three");
        assertThat(list).hasSize(3)
                .contains("three")
                .contains("second", Index.atIndex(1))
                .doesNotContain("two")
                .containsAnyOf("first", "second")
                .containsExactlyInAnyOrder("first", "second", "three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first1", "second1", "three1", "four1", "four1", "five1", "five1");
        assertThat(set).hasSize(5)
                .contains("five1")
                .allSatisfy(s -> s.contains("1"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "three", "three", "four", "five");
        assertThat(map).hasSize(4)
                .contains(Map.entry("first", 0))
                .contains(Map.entry("five", 4))
                .doesNotContain(Map.entry("two",0));
    }
}