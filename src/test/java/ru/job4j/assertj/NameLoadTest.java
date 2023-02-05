package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParseEmptyArrayThenException() {
        NameLoad nameLoad = new NameLoad();
        String[] strings = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void whenParseStringWithMissEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("a=1", "sa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining("=");
    }

    @Test
    void whenParseString() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=1", "s=a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining("=1");
    }

    @Test
    void whenParseString2() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("1=", "s=a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining("1=");
    }
}