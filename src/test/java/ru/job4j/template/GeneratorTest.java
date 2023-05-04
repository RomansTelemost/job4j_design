package ru.job4j.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class GeneratorTest {

    private Map<String, String> map = new HashMap<>();

    private static String TEMPLATE = "I am a ${name}, Who are ${subject}?";
    private static final String EXPECTED = "I am a name1, Who are subject1?";

    @BeforeEach
    public void beforeEach() {
        map.put("name", "name1");
        map.put("subject", "subject1");
    }

    @Test
    public void whenCountOfTemplateParamsEqualsMapParamsThenNotException() {
        Generator generator = new TemplateGenerator();
        String result = generator.produce(TEMPLATE, map);
        assertThat(result).isEqualTo(EXPECTED);
    }

    @Test
    public void whenNotEnoughMapParamsThenException() {
        Generator generator = new TemplateGenerator();
        map.remove("name");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> generator.produce(TEMPLATE, map));
        String expected = "Not enough map params";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    public void whenTooMachMapParamsThenException() {
        Generator generator = new TemplateGenerator();
        map.put("suriname", "suriname1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> generator.produce(TEMPLATE, map));
        String expected = "Too mach map params";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }
}