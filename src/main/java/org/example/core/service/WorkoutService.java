package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис для работы с тренировками.
 */
public class WorkoutService {

    private final UserService userService = new UserService();

    /**
     * Добавляет тренировку пользователю.
     *
     * @param currentUser текущий пользователь
     * @param date         дата тренировки
     * @param type         тип тренировки
     * @param duration     продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     */
    public void addWorkout(User currentUser, LocalDate date, String type, int duration, int caloriesBurned, Map<String, Object> additionalParameters) {
        Workout workout = new Workout(date, type, duration, caloriesBurned);
        workout.setAdditionalParameters(additionalParameters);
        userService.addWorkout(workout, currentUser);
    }

    /**
     * Редактирует тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     * @param id          идентификатор тренировки
     * @param date        дата тренировки
     * @param type        тип тренировки
     * @param duration    продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     */
    public void editWorkout(User currentUser, int id, LocalDate date, String type, int duration, int caloriesBurned) {
        Workout workout = currentUser.getWorkoutById(id);
        if (workout != null) {
            if (date != null) {
                workout.setDate(date);
            }
            if (type != null) {
                workout.setType(type);
            }
            if (duration > 0) {
                workout.setDuration(duration);
            }
            if (caloriesBurned > 0) {
                workout.setCaloriesBurned(caloriesBurned);
            }
        }
    }

    /**
     * Удаляет тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     * @param id          идентификатор тренировки
     * @return `true`, если тренировка была удалена, иначе `false`
     */
    public boolean deleteWorkout(User currentUser, int id) {
        return currentUser.removeWorkoutById(id);
    }

    /**
     * Получает общее количество сожженных калорий пользователем.
     *
     * @param currentUser текущий пользователь
     * @return общее количество сожженных калорий
     */
    public int getCaloriesBurnedTotal(User currentUser) {
        return currentUser.getWorkouts().values().stream()
                .mapToInt(Workout::getCaloriesBurned)
                .sum();
    }

    /**
     * Получает статистику сожженных калорий по датам для пользователя.
     *
     * @param currentUser текущий пользователь
     * @return статистика сожженных калорий по датам
     */
    public Map<LocalDate, Integer> getCaloriesBurnedByDate(User currentUser) {
        Map<LocalDate, Integer> statisticsByDate = new HashMap<>();
        currentUser.getWorkouts().values().forEach(workout -> {
            LocalDate date = workout.getDate();
            int caloriesBurned = workout.getCaloriesBurned();
            statisticsByDate.merge(date, caloriesBurned, Integer::sum);
        });
        return statisticsByDate;
    }

    /**
     * Получает общую продолжительность тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     * @return общая продолжительность тренировок в минутах
     */
    public int getTotalDuration(User currentUser) {
        return currentUser.getWorkouts().values().stream()
                .mapToInt(Workout::getDuration)
                .sum();
    }

    /**
     * Получает отфильтрованный список тренировок.
     *
     * @param startDate   начальная дата диапазона фильтрации
     * @param endDate     конечная дата диапазона фильтрации
     * @param type        тип тренировки для фильтрации
     * @return отфильтрованный список тренировок
     */
    public List<Workout> getFilteredWorkouts(Map<Integer, Workout> workouts, LocalDate startDate, LocalDate endDate, String type) {
        return workouts.values().stream()
                .filter(workout -> (startDate == null || workout.getDate().isAfter(startDate))
                                   && (endDate == null || workout.getDate().isBefore(endDate))
                                   && (type == null || workout.getType().equals(type)))
                .collect(Collectors.toList());
    }
}
