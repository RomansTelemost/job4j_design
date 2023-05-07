package ru.job4j.ood.srp;

import java.util.Random;

public class ExampleGenerator1 {

    public static void main(String[] args) {
        NumberGeneratorExample1 numberGeneratorExample1 = new NumberGeneratorExample1(new Random());
        SimpleSequenceGenerator simpleSequenceGenerator = new SimpleSequenceGenerator(numberGeneratorExample1);
        System.out.println(simpleSequenceGenerator.generate(100));
    }
}
