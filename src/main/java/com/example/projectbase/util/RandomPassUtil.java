package com.example.projectbase.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPassUtil {
    public static String random() {
        int length = 6;
        return RandomStringUtils.random(length, true, true);
    }
}
