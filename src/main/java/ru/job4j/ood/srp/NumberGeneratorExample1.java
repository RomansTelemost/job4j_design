package ru.job4j.ood.srp;

import java.util.Random;

public class NumberGeneratorExample1 implements NumberGenerator {

    private final Random random;

    public NumberGeneratorExample1(Random random) {
        this.random = random;
    }

    @Override
    public Object generate() {
        return random.nextInt();
    }
}
