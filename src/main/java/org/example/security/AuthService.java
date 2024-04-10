package org.example.security;

import org.example.core.domain.User;
import org.example.core.service.UserService;
import org.example.logger.Logger;

import java.io.Console;
import java.util.*;

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

        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль не доступна, используйте другой способ ввода пароля.");
            return null;
        }

        char[] passwordArray1 = console.readPassword("Введите пароль:");
        String password1 = new String(passwordArray1);

        char[] passwordArray2 = console.readPassword("Повторите пароль:");
        String password2 = new String(passwordArray2);

        if (!password1.equals(password2)) {
            System.out.println("Пароли не совпадают. Попробуйте снова.");
            return null;
        }

        boolean isAdmin = role.equals("admin");
        User user = userService.addUser(username, password1, isAdmin);
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
        if (console == null) {
            System.out.println("Консоль не доступна, используйте другой способ ввода пароля.");
            return null;
        }

        char[] passwordArray = console.readPassword("Введите пароль:");

        List<User> users = userService.getAllUsers();
        User currentUser = users.stream()
                .filter(u -> u.getUsername().equals(username) && Arrays.equals(u.getPassword().toCharArray(), passwordArray))
                .findFirst()
                .orElse(null);
        if (currentUser == null) {
            System.out.println("Неверный логин или пароль");
        }
        Logger.log("Авторизация пользователя", currentUser != null ? currentUser.getUsername() : "Unknown", true);
        return currentUser;
    }

}