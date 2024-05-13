package org.example.in.comminication;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.service.impl.WorkoutServiceImpl;
import org.example.in.validation.InputValidator;
import org.example.logger.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Класс для взаимодействия с пользователем для получения статистики по тренировкам.
 */
public class StatisticsUserInteraction {

    private final WorkoutServiceImpl workoutService = new WorkoutServiceImpl();
    private final InputValidator inputValidator = new InputValidator();
    private final Logger logger = new Logger();

    /**
     * Получает общее количество сожженных калорий пользователем.
     *
     * @param currentUser текущий пользователь
     */
    public void getCaloriesBurnedTotal(User currentUser) throws SQLException {

        int totalCaloriesBurned = workoutService.getCaloriesBurnedTotal(currentUser);

        logger.log("Получение статистики расхода калорий всего", currentUser.getUsername(), true);

        System.out.println("Всего потрачено калорий:");
        System.out.println(totalCaloriesBurned);
    }

    /**
     * Получает статистику сожженных калорий по датам для пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void getCaloriesBurnedByDate(User currentUser) {
        Map<String, Integer> totalCaloriesBurned = workoutService.getCaloriesBurnedByDate(currentUser);

        logger.log("Получение статистики расхода калорий по дням", currentUser.getUsername(), true);

        System.out.println("Потрачено калорий:");
        System.out.println(totalCaloriesBurned);
    }

    /**
     * Получает общую продолжительность тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void getDurationTotal(User currentUser) {

        double totalDuration = workoutService.getTotalDuration(currentUser);

        logger.log("Получение статистики продолжительности занятий", currentUser.getUsername(), true);

        System.out.println("Всего затрачено часов:");
        System.out.printf("%.2f%n", totalDuration / 60);
    }

    /**
     * Получает отфильтрованный список тренировок в соответствии с пользовательским вводом.
     *
     * @param currentUser текущий пользователь
     */
    public void getFilteredWorkouts(User currentUser) {
        LocalDate startDate = inputValidator.promptForDate("Введите начальную дату (формат гггг-мм-дд):");
        LocalDate endDate = inputValidator.promptForDate("Введите конечную дату (формат гггг-мм-дд):");
        Long typeId = (long) inputValidator.promptForPositiveInt("Введите тип тренировки:");

        Map<String, List<WorkoutDTO>> filteredWorkouts = workoutService.getFilteredWorkouts(currentUser.getId(), startDate, endDate, typeId);

        logger.log("Получение отфильтрованного списка тренировок", currentUser.getUsername(), true);

        for (Map.Entry<String, List<WorkoutDTO>> entry : filteredWorkouts.entrySet()) {
            System.out.println("Отфильтрованные тренировки:");
            for (WorkoutDTO workoutDTO : entry.getValue()) {
                System.out.println(workoutDTO);
            }
        }
    }

}
