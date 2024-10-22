package org.spring.utils;

import java.util.Scanner;

public class ScannerUtil {
    public static Scanner instance;

    private ScannerUtil() {

    }

    public static Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;

    }
}
