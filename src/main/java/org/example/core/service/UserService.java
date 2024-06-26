package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.enums.UserRole;
import org.example.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с пользователями и тренировками.
 */
public class UserService {

    private static final Map<String, User> users = new HashMap<>();

    /**
     * Добавляет нового пользователя.
     *
     * @param username имя пользователя
     * @param password пароль
     * @param role роль пользователя
     * @return созданный пользователь
     */
    public User addUser(String username, String password, UserRole role) {
        User user = new User(username, password, role);
        users.put(username, user);
        return user;
    }

    /**
     * Получает список всех пользователей.
     *
     * @return список пользователей
     */
    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }

    /**
     * Добавляет тренировку пользователю.
     *
     * @param workout тренировка
     * @param currentUser текущий пользователь
     */
    public void addWorkout(Workout workout, User currentUser) {
        User user = getUserByUsername(currentUser.getUsername());
        if (user == null) {
            System.out.println("Пользователь не найден.");
            return;
        }

        for (Workout existingWorkout : user.getWorkouts().values()) {
            if (existingWorkout.getDate().equals(workout.getDate()) && existingWorkout.getType().equals(workout.getType())) {
                System.out.println("Тренировка этого типа уже добавлена на эту дату.");
                Logger.log("Добавление тренировки", currentUser.getUsername(), false);
                return;
            }
        }

        System.out.println("Тренировка добавлена");
        Logger.log("Добавление тренировки", currentUser.getUsername(), true);
        user.addWorkout(workout);
    }

    /**
     * Получает пользователя по его имени.
     *
     * @param username имя пользователя
     * @return пользователь или `null`, если пользователь не найден
     */
    public User getUserByUsername(String username) {
        return users.get(username);
    }
}
