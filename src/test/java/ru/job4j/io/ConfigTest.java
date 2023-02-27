package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComments() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("PostgreSQL")).isNull();
    }

    @Test
    void whenPairWithoutKeyOrValueThenException() {
        String path = "./data/pair_without_keyOrValue.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            config.load();
        });
        assertThat(exception.getMessage()).isEqualTo("In line 'hibernate.dialect=' not found key or value!");
    }
}