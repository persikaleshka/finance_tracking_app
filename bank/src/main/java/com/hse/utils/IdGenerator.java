package com.hse.utils;

import java.util.UUID;

public class IdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String generateShort() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}