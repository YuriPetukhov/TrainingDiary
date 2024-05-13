package org.example.in.comminication;

import org.example.core.domain.User;
import org.example.in.validation.InputValidator;
import org.example.security.AuthService;

import java.sql.SQLException;

/**
 * Основной класс для взаимодействия с пользователем, позволяющий пользователям регистрироваться или проходить аутентификацию.
 */
public class AuthUserInteraction {

    private final AuthService authService = new AuthService();
    private final InputValidator inputValidator = new InputValidator();


    /**
     * Запрашивает у пользователя ввод имени пользователя, пароля и роли и регистрирует нового пользователя.
     *
     * @return вновь зарегистрированный пользователь.
     */
    public User registerUser() throws SQLException {
        String username = inputValidator.promptForNonEmptyInput("Введите имя пользователя:");
        String role = inputValidator.promptForRole();
        return authService.registerUser(username, role);
    }

    /**
     * Запрашивает у пользователя ввод имени пользователя и пароля и аутентифицирует пользователя.
     *
     * @return аутентифицированный пользователь или `null`, если учетные данные пользователя недействительны.
     */
    public User authenticateUser() {
        String username = inputValidator.promptForNonEmptyInput("Введите имя пользователя:");
        try {
            return authService.authenticateUser(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

