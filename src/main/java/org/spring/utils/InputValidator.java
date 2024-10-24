package org.spring.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.ConsoleUI.TeamMenu;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner =ScannerUtil.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TeamMenu.class);

    public static int validatePositiveInteger() {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value < 0) {
                    logger.info("Value must be a positive integer.");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input. Please enter a valid positive integer.");
                scanner.next();
            }
        }
    }

    public static int validateAge() {
        int age;
        while (true) {
            try {
                age = scanner.nextInt();
                if (age < 16) {
                    logger.info("Age must be at least 16.");
                } else {
                    return age;
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    public static double validatePositiveDouble() {
        double value;
        while (true) {
            try {
                value = scanner.nextDouble();
                if (value < 0) {
                    logger.info("Value must be positive.");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
               logger.warn("Invalid input. Please enter a valid positive number.");
                scanner.next();
            }
        }
    }

    public static <T extends Enum<T>> T validateEnum(Class<T> enumClass) {
        while (true) {
            String input = scanner.next().toUpperCase();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid option from: " + String.join(", ", getEnumNames(enumClass)));
            }
        }
    }

    private static <T extends Enum<T>> String[] getEnumNames(Class<T> enumClass) {
        T[] enums = enumClass.getEnumConstants();
        String[] names = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            names[i] = enums[i].name();
        }
        return names;
    }

    public static LocalDate validateDate( boolean isStartDate) {
        LocalDate date;
        while (true) {
            date = LocalDate.parse(scanner.next());
            LocalDate today = LocalDate.now();
            if (isStartDate && date.isBefore(today)) {
               logger.warn("Start date must not be before today.");
            } else if (!isStartDate && date.isBefore(today)) {
                logger.warn("End date must not be before today.");
            } else if (!isStartDate && date.isBefore(LocalDate.now())) {
                logger.warn("End date must be after the start date.");
            } else {
                return date;
            }
        }
    }
}
