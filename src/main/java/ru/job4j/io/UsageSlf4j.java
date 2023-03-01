package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageSlf4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        byte b = 1;
        short s = 2;
        int age = 33;
        long l = 4;
        double d = 5.4d;
        float f = 6.6f;
        boolean bool = true;
        char c = 'c';
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Byte : {}, short : {}, int : {}, long : {}, double : {}, float : {}, char : {}, bool : {}", b, s, age, l, d, f, c, bool);
    }
}
