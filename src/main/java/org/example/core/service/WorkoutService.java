package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.service.filters.DateRangeFilter;
import org.example.core.service.filters.TypeFilter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Сервис для работы с тренировками.
 */
public class WorkoutService {

    /**
     * Добавляет тренировку пользователю.
     *
     * @param currentUser текущий пользователь
     * @param date дата тренировки
     * @param type тип тренировки
     * @param duration продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     * @param additionalParameters дополнительные параметры тренировки
     */
    public void addWorkout(User currentUser, LocalDate date, String type, int duration, int caloriesBurned, Map<String, Object> additionalParameters) {

        Workout workout = new Workout(date, type, duration, caloriesBurned);
        workout.setAdditionalParameters(additionalParameters);

        currentUser.getWorkouts().add(workout);
    }

    /**
     * Редактирует тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     * @param id идентификатор тренировки
     * @param date дата тренировки
     * @param type тип тренировки
     * @param duration продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     */
    public void editWorkout(User currentUser, int id, LocalDate date, String type, int duration, int caloriesBurned) {
        Workout workout = currentUser.getWorkoutById(id);
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

    /**
     * Удаляет тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     * @param id идентификатор тренировки
     * @return `true`, если тренировка была удалена, иначе `false`
     */
    public boolean deleteWorkout(User currentUser, int id) {

        Workout workout = currentUser.getWorkoutById(id);
        if (workout == null) {
            return false;
        }
        currentUser.removeWorkout(workout);
        return true;
    }

    /**
     * Получает общее количество сожженных калорий пользователем.
     *
     * @param currentUser текущий пользователь
     * @return общее количество сожженных калорий
     */
    public int getCaloriesBurnedTotal(User currentUser) {

        List<Workout> workouts = currentUser.getWorkouts();

        int totalCaloriesBurned = 0;
        for (Workout workout : workouts) {
            totalCaloriesBurned += workout.getCaloriesBurned();
        }
        return totalCaloriesBurned;
    }

    /**
     * Получает статистику сожженных калорий по датам для пользователя.
     *
     * @param currentUser текущий пользователь
     * @return статистика сожженных калорий по датам
     */
    public Map<LocalDate, Integer> getCaloriesBurnedByDate(User currentUser) {
        List<Workout> workouts = currentUser.getWorkouts();

        Map<LocalDate, Integer> statisticsByDate = new HashMap<>();
        for (Workout workout : workouts) {
            LocalDate date = workout.getDate();
            int caloriesBurned = workout.getCaloriesBurned();

            statisticsByDate.merge(date, caloriesBurned, Integer::sum);
        }
        return statisticsByDate;
    }

    /**
     * Проверяет, существует ли тренировка с указанным идентификатором у пользователя.
     *
     * @param currentUser текущий пользователь
     * @param id идентификатор тренировки
     * @return `true`, если тренировка существует, иначе `false`
     */
    public boolean checkWorkoutById(User currentUser, int id) {
        Workout workout = currentUser.getWorkoutById(id);
        return workout != null;
    }

    /**
     * Получает общую продолжительность тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     * @return общая продолжительность тренировок в минутах
     */
    public int getTotalDuration(User currentUser) {

        List<Workout> workouts = currentUser.getWorkouts();

        int totalDuration = 0;
        for (Workout workout : workouts) {
            totalDuration += workout.getDuration();
        }
        return totalDuration;
    }

    /**
     * Получает отфильтрованный список тренировок.
     *
     * @param workouts  список всех тренировок
     * @param startDate начальная дата диапазона фильтрации
     * @param endDate   конечная дата диапазона фильтрации
     * @param type      тип тренировки для фильтрации
     * @return отфильтрованный список тренировок
     */
    public List<Workout> getFilteredWorkouts(List<Workout> workouts, LocalDate startDate, LocalDate endDate, String type) {
        Predicate<Workout> predicate = workout -> true;

        if (startDate != null && endDate != null) {
            predicate = predicate.and(new DateRangeFilter(startDate, endDate)::test);
        }

        if (type != null) {
            predicate = predicate.and(new TypeFilter(type)::test);
        }

        return workouts.stream().filter(predicate).collect(Collectors.toList());
    }
}
