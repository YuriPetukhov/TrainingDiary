package org.example.security;

import org.example.core.domain.User;
import org.example.core.enums.UserRole;
import org.example.core.service.UserService;
import org.example.logger.Logger;

import java.io.Console;
import java.util.List;

import static org.example.in.InputValidator.promptForNonEmptyInput;

/**
 * Сервис для работы с авторизацией и регистрацией пользователей.
 */
public class AuthService {

    private static final UserService userService = new UserService();

    /**
     * Регистрирует нового пользователя.
     *
     * @param username имя пользователя
     * @param role     роль пользователя
     * @return зарегистрированный пользователь
     */
    public static User registerUser(String username, String role) {

        String password1 = null;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль не доступна, поэтому пароль будет выведен на экран.");
            password1 = promptForNonEmptyInput("Введите пароль:");
        } else {

            char[] passwordArray1 = console.readPassword("Введите пароль:");
            password1 = new String(passwordArray1);

            char[] passwordArray2 = console.readPassword("Повторите пароль:");
            String password2 = new String(passwordArray2);

            if (!password1.equals(password2)) {
                System.out.println("Пароли не совпадают. Попробуйте снова.");
                return null;
            }
        }
        UserRole userRole = UserRole.USER;
        if (role.equals(UserRole.ADMIN.toString().toLowerCase())) {
            userRole = UserRole.ADMIN;
        }
        User user = userService.addUser(username, password1, userRole);
        Logger.log("Регистрация пользователя", user.getUsername(), true);
        return user;
    }

    /**
     * Аутентифицирует пользователя.
     *
     * @param username имя пользователя
     * @return аутентифицированный пользователь или null, если пользователь не найден
     */
    public static User authenticateUser(String username) {
        Console console = System.console();
        String password = null;
        if (console == null) {
            System.out.println("Консоль не доступна, поэтому пароль будет выведен на экран.");
            password = promptForNonEmptyInput("Введите пароль:");
        } else {
            char[] passwordArray = console.readPassword("Введите пароль:");
            password = new String(passwordArray);
        }

        List<User> users = userService.getAllUsers();
        String finalPassword = password;
        User currentUser = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(finalPassword))
                .findFirst()
                .orElse(null);
        if (currentUser == null) {
            System.out.println("Неверный логин или пароль");
        }
        Logger.log("Авторизация пользователя", currentUser != null ? currentUser.getUsername() : "Unknown", true);
        return currentUser;
    }


}