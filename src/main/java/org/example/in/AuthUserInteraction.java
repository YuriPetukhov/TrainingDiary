package org.example.in;

import org.example.core.domain.User;
import org.example.logger.Logger;
import org.example.security.AuthService;

import java.util.Scanner;

import static org.example.in.InputValidator.promptForNonEmptyInput;
import static org.example.in.InputValidator.promptForRole;

/**
 * Основной класс для взаимодействия с пользователем, позволяющий пользователям регистрироваться или проходить аутентификацию.
 */
public class AuthUserInteraction {

    private static User currentUser;

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Предъявляет пользователю меню и позволяет ему зарегистрироваться или пройти аутентификацию.
     *
     * @return аутентифицированный пользователь или `null`, если пользователь решил выйти из программы.
     */
    public static User registerOrauthenticateUser() {

        System.out.println("Выберите действие:");
        System.out.println("1. Зарегистрировать нового пользователя");
        System.out.println("2. Авторизоваться");
        System.out.println("3. Завершить программу");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> currentUser = registerUser();
            case 2 -> currentUser = authenticateUser();
            case 3 -> {
                System.out.println("До свидания!");
                return null;
            }
            default -> System.out.println("Неверный выбор");
        }
        return currentUser;
    }

    /**
     * Запрашивает у пользователя ввод имени пользователя, пароля и роли и регистрирует нового пользователя.
     *
     * @return вновь зарегистрированный пользователь.
     */
    private static User registerUser() {
        String username = promptForNonEmptyInput("Введите имя пользователя:");
        String role = promptForRole();
        return AuthService.registerUser(username, role);
    }

    /**
     * Запрашивает у пользователя ввод имени пользователя и пароля и аутентифицирует пользователя.
     *
     * @return аутентифицированный пользователь или `null`, если учетные данные пользователя недействительны.
     */
    private static User authenticateUser() {
        String username = promptForNonEmptyInput("Введите имя пользователя:");
        return AuthService.authenticateUser(username);
    }

}

