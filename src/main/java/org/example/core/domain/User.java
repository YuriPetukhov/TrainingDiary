package org.example.core.domain;

import java.util.ArrayList;
import java.util.List;
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
     * Флаг, указывающий, является ли пользователь администратором.
     */
    private final boolean isAdmin;

    /**
     * Список тренировок пользователя.
     */
    private final List<Workout> workouts;

    /**
     * Конструктор класса User.
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param isAdmin флаг, указывающий, является ли пользователь администратором
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.workouts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    /**
     * Возвращает тренировку по ее идентификатору.
     *
     * @param id идентификатор тренировки
     * @return тренировка с указанным идентификатором или null, если такой тренировки нет
     */
    public Workout getWorkoutById(int id) {
        for (Workout workout : workouts) {
            if (workout.getId() == id) {
                return workout;
            }
        }
        return null;
    }

    /**
     * Удаляет тренировку из списка тренировок пользователя.
     *
     * @param workout тренировка, которую нужно удалить
     */
    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isAdmin == user.isAdmin && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, isAdmin);
    }
}
