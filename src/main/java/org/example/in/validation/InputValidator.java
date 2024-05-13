package org.example.in.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для валидации ввода пользователя.
 */
public class InputValidator {

    private static final int MAX_ATTEMPTS = 3;

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Запрашивает у пользователя непустой ввод.
     *
     * @param message сообщение, которое будет выводиться пользователю
     * @return введенное пользователем значение
     */
    public String promptForNonEmptyInput(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Значение не может быть пустым. Пожалуйста, введите значение.");
            }
        } while (input.isEmpty());
        return input;
    }

    public String promptForInput(String message) {
        String input;
        System.out.println(message);
        input = scanner.nextLine().trim();
        return input;
    }

    /**
     * Запрашивает у пользователя роль (user/admin).
     *
     * @return введенная пользователем роль
     */
    public String promptForRole() {
        String role;
        do {
            role = promptForNonEmptyInput("Введите роль (user/admin):").toLowerCase();
            if (!role.equals("user") && !role.equals("admin")) {
                System.out.println("Неверная роль. Пожалуйста, введите 'user' или 'admin'.");
            }
        } while (!role.equals("user") && !role.equals("admin"));
        return role;
    }

    /**
     * Запрашивает у пользователя дату в формате гггг-мм-дд.
     *
     * @param message сообщение, которое будет выводиться пользователю
     * @return введенная пользователем дата
     */
    public LocalDate promptForDate(String message) {
        LocalDate date;
        do {
            String input = promptForNonEmptyInput(message);
            try {
                date = LocalDate.parse(input);
                break;
            } catch (Exception e) {
                System.out.println("Неверный формат даты. Пожалуйста, введите дату в формате гггг-мм-дд.");
            }
        } while (true);
        return date;
    }

    /**
     * Запрашивает у пользователя положительное целое число.
     *
     * @param message сообщение, которое будет выводиться пользователю
     * @return введенное пользователем положительное целое число
     */
    public int promptForPositiveInt(String message) {
        int value;
        do {
            String input = promptForNonEmptyInput(message);
            try {
                value = Integer.parseInt(input);
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Значение должно быть положительным целым числом.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, введите целое положительное число.");
            }
        } while (true);
        return value;
    }

    /**
     * Разбирает строку ввода в дату.
     *
     * @param inputDate строка ввода
     * @return дата, соответствующая строке ввода
     */
    public LocalDate parseDate(String inputDate) {
        LocalDate date = null;
        while (!inputDate.isEmpty()) {
            if (inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    date = LocalDate.parse(inputDate);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат даты. Пожалуйста, введите дату в формате гггг-мм-дд.");
                }
            } else {
                System.out.println("Неверный формат даты. Пожалуйста, введите дату в формате гггг-мм-дд.");
            }
            inputDate = scanner.nextLine().trim();
        }
        return date;
    }

    /**
     * Валидирует введенное пользователем положительное целое число.
     *
     * @param input строка ввода
     * @return валидное положительное целое число
     */
    public int validatePositiveInt(String input) {
        int value = 0;
        while (!input.isEmpty()) {
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("Значение должно быть положительным целым числом.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, введите целое положительное число.");
            }
            input = scanner.nextLine().trim();
        }
        return value;
    }

    public boolean containsIgnoreCase(List<String> list, String element) {
        for (String item : list) {
            if (item.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return false;
    }

    public int promptForNumberChoose(int min, int max) {
        int value = 0;
        int attempts = 0;
        do {
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Значение должно быть целым числом от " + min + " до " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, введите целое число.");
            }
            attempts++;
        } while (attempts < MAX_ATTEMPTS);
        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Превышено количество попыток. Завершение программы.");
            System.exit(1);
        }
        return value;
    }

    public Integer validateNeuValue() {
        int newValue = 0;
        String typeInput = scanner.nextLine().trim();
        if (!typeInput.isEmpty()) {
            newValue = validatePositiveInt(typeInput);
        }
        return newValue;
    }
}
