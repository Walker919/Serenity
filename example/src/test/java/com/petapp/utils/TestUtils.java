package com.petapp.utils;

import java.security.SecureRandom;

public class TestUtils {
    public static String getRandomValue() {
        SecureRandom random = new SecureRandom();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    public static int getRandomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(100000);
    }
}
