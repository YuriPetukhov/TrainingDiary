package org.example.core.domain;

import org.example.core.enums.UserRole;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс User представляет собой модель пользователя.
 */
public class User {

    /**
     * Имя пользователя.
     */
    private final String username;

    /**
     * Пароль пользователя.
     */
    private final String password;

    /**
     * Роль пользователя.
     */
    private final UserRole role;

    /**
     * Мапа тренировок пользователя, где ключом является ID тренировки, а значением - сама тренировка.
     */
    private final Map<Integer, Workout> workouts;

    /**
     * Конструктор класса User.
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     */
    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.workouts = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Map<Integer, Workout> getWorkouts() {
        return workouts;
    }

    /**
     * Возвращает тренировку по ее идентификатору.
     *
     * @param id идентификатор тренировки
     * @return тренировка с указанным идентификатором или null, если такой тренировки нет
     */
    public Workout getWorkoutById(int id) {
        return workouts.get(id);
    }

    /**
     * Добавляет тренировку в мапу тренировок пользователя.
     *
     * @param workout тренировка, которую нужно добавить
     */
    public void addWorkout(Workout workout) {
        workouts.put(workout.getId(), workout);
    }

    /**
     * Удаляет тренировку из мапы тренировок пользователя.
     *
     * @param id тренировки, которую нужно удалить
     */
    public boolean removeWorkoutById(int id) {
        if (workouts.containsKey(id)) {
            workouts.remove(id);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return role == user.role && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }
}
