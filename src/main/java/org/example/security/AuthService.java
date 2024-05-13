package org.example.security;

import org.example.core.domain.User;
import org.example.core.enums.UserRole;
import org.example.core.service.impl.UserServiceImpl;
import org.example.in.validation.InputValidator;
import org.example.logger.Logger;

import java.io.Console;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Сервис для работы с авторизацией и регистрацией пользователей.
 */
public class AuthService {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final InputValidator inputValidator = new InputValidator();
    private final Logger logger = new Logger();

    /**
     * Регистрирует нового пользователя.
     *
     * @param username имя пользователя
     * @param role     роль пользователя
     * @return зарегистрированный пользователь
     */
    public User registerUser(String username, String role) throws SQLException {

        String password1 = null;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль не доступна, поэтому пароль будет выведен на экран.");
            password1 = inputValidator.promptForNonEmptyInput("Введите пароль:");
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
        if (user != null) {
            logger.log("Регистрация пользователя", user.getUsername(), true);
        } else {
            logger.log("Регистрация пользователя", username, false);
        }
        return user;
    }

    /**
     * Аутентифицирует пользователя.
     *
     * @param username имя пользователя
     * @return аутентифицированный пользователь или null, если пользователь не найден
     */
    public User authenticateUser(String username) throws SQLException {
        Console console = System.console();
        String password = null;
        if (console == null) {
            System.out.println("Консоль не доступна, поэтому пароль будет выведен на экран.");
            password = inputValidator.promptForNonEmptyInput("Введите пароль:");
        } else {
            char[] passwordArray = console.readPassword("Введите пароль:");
            password = new String(passwordArray);
        }

        Optional<User> optCurrentUser = userService.findByUsername(username);
        if (optCurrentUser.isPresent() && optCurrentUser.get().getPassword().equals(password)) {
            logger.log("Авторизация пользователя", optCurrentUser.get().getUsername(), true);
            return optCurrentUser.get();
        } else {
            System.out.println("Неверный логин или пароль");
            return null;
        }
    }
}