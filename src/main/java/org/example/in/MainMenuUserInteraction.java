package org.example.in;

import org.example.core.domain.User;
import org.example.logger.Logger;

import java.util.*;

import static org.example.in.AuthUserInteraction.registerOrauthenticateUser;

/**
 * Класс для взаимодействия с пользователем в главном меню.
 */
public class MainMenuUserInteraction {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Запускает главное меню.
     */
    public void run() {

        User currentUser = registerOrauthenticateUser();
        getMainMenu(currentUser);
    }

    /**
     * Выводит главное меню и обрабатывает выбор пользователя.
     *
     * @param currentUser текущий пользователь
     */
    private void getMainMenu(User currentUser) {
        boolean shouldExit = false;
        while (currentUser != null && !shouldExit) {

            System.out.println("Выберите действие:");
            System.out.println("1. Добавить тренировку");
            System.out.println("2. Просмотреть тренировки");
            System.out.println("3. Редактировать тренировку");
            System.out.println("4. Удалить тренировку");
            System.out.println("5. Получить статистику тренировок");
            System.out.println("6. Сменить пользователя");
            System.out.println("7. Завершить программу");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> WorkoutUserInteraction.addWorkout(currentUser);
                case 2 -> WorkoutUserInteraction.viewWorkouts(currentUser);
                case 3 -> WorkoutUserInteraction.editWorkout(currentUser);
                case 4 -> WorkoutUserInteraction.deleteWorkout(currentUser);
                case 5 -> {
                    getStatisticsMenu(currentUser);
                    shouldExit = true;
                }
                case 6 -> currentUser = registerOrauthenticateUser();
                case 7, 0 -> {
                    System.out.println("До свидания!");
                    Logger.log("Завершение программы", currentUser.getUsername(), true);
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    /**
     * Выводит меню статистики и обрабатывает выбор пользователя.
     *
     * @param currentUser текущий пользователь
     */
    private void getStatisticsMenu(User currentUser) {

        boolean returnToMainMenu = false;

        while (currentUser != null && !returnToMainMenu) {
            System.out.println("Выберите действие:");
            System.out.println("1. Статистика расхода калорий всего");
            System.out.println("2. Статистика расхода калорий по дням");
            System.out.println("3. Статистика общего количества часов занятий");
            System.out.println("4. Статистика по виду тренировки в заданный интервал времени");
            System.out.println("5. Вернуться в главное меню");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> StatisticsUserInteraction.getCaloriesBurnedTotal(currentUser);
                case 2 -> StatisticsUserInteraction.getCaloriesBurnedByDate(currentUser);
                case 3 -> StatisticsUserInteraction.getDurationTotal(currentUser);
                case 4 -> StatisticsUserInteraction.getFilteredWorkouts(currentUser);
                case 5 -> returnToMainMenu = true;
                default -> System.out.println("Неверный выбор");
            }
        }
        if (currentUser != null) {
            getMainMenu(currentUser);
        }
    }

}
