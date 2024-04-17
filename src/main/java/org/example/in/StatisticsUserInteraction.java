package org.example.in;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.service.WorkoutService;
import org.example.logger.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.in.InputValidator.promptForDate;
import static org.example.in.InputValidator.promptForNonEmptyInput;

/**
 * Класс для взаимодействия с пользователем для получения статистики по тренировкам.
 */
public class StatisticsUserInteraction {

    private static final WorkoutService workoutService = new WorkoutService();

    /**
     * Получает общее количество сожженных калорий пользователем.
     *
     * @param currentUser текущий пользователь
     */
    public static void getCaloriesBurnedTotal(User currentUser) {

        int totalCaloriesBurned = workoutService.getCaloriesBurnedTotal(currentUser);

        Logger.log("Получение статистики расхода калорий всего", currentUser.getUsername(), true);

        System.out.println("Всего потрачено калорий:");
        System.out.println(totalCaloriesBurned);
    }

    /**
     * Получает статистику сожженных калорий по датам для пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void getCaloriesBurnedByDate(User currentUser) {
        Map<LocalDate, Integer> totalCaloriesBurned = workoutService.getCaloriesBurnedByDate(currentUser);

        Logger.log("Получение статистики расхода калорий по дням", currentUser.getUsername(), true);

        System.out.println("Потрачено калорий:");
        System.out.println(totalCaloriesBurned);
    }

    /**
     * Получает общую продолжительность тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void getDurationTotal(User currentUser) {

        int totalDuration = workoutService.getTotalDuration(currentUser);

        Logger.log("Получение статистики продолжительности занятий", currentUser.getUsername(), true);

        System.out.println("Всего затрачено часов:");
        System.out.println(totalDuration);
    }

    /**
     * Получает отфильтрованный список тренировок в соответствии с пользовательским вводом.
     *
     * @param currentUser текущий пользователь
     */
    public static void getFilteredWorkouts(User currentUser) {
        LocalDate startDate = promptForDate("Введите начальную дату (формат гггг-мм-дд):");
        LocalDate endDate = promptForDate("Введите конечную дату (формат гггг-мм-дд):");
        String type = promptForNonEmptyInput("Введите тип тренировки:");

        List<Workout> filteredWorkouts = workoutService.getFilteredWorkouts(currentUser.getWorkouts(), startDate, endDate, type);

        Logger.log("Получение отфильтрованного списка тренировок", currentUser.getUsername(), true);

        System.out.println("Отфильтрованные тренировки:");
        for (Workout workout : filteredWorkouts) {
            System.out.println(workout);
        }
    }


}
