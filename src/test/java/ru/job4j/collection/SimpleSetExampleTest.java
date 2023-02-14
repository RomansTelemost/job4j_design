package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetExampleTest {

    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSetExample set = new SimpleSetExample();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }
}