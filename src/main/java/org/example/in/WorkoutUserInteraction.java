package org.example.in;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.service.UserService;
import org.example.core.service.WorkoutService;
import org.example.logger.Logger;

import java.time.LocalDate;
import java.util.*;

import static org.example.in.InputValidator.*;

/**
 * Класс для взаимодействия с пользователем для работы с тренировками.
 */
public class WorkoutUserInteraction {

    private static final Scanner scanner = new Scanner(System.in);

    private static final WorkoutService workoutService = new WorkoutService();
    private static final UserService userService = new UserService();

    /**
     * Добавляет новую тренировку для пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void addWorkout(User currentUser) {

        LocalDate date = promptForDate("Введите дату тренировки (формат гггг-мм-дд):");
        String type = promptForNonEmptyInput("Введите тип тренировки:");
        int duration = promptForPositiveInt("Введите длительность тренировки (в минутах):");
        int caloriesBurned = promptForPositiveInt("Введите количество потраченных калорий:");

        Map<String, Object> additionalParameters = addAdditionalParameters();

        workoutService.addWorkout(currentUser, date, type, duration, caloriesBurned, additionalParameters);
    }

    /**
     * Добавляет дополнительные параметры к тренировке.
     *
     * @return карта дополнительных параметров
     */
    private static Map<String, Object> addAdditionalParameters() {
        Map<String, Object> additionalParameters = new HashMap<>();
        String parameterName;
        do {
            System.out.println("Введите название дополнительного параметра (или нажмите Enter для завершения):");
            parameterName = scanner.nextLine().trim();
            if (!parameterName.isEmpty()) {
                if (additionalParameters.containsKey(parameterName)) {
                    System.out.println("Параметр с названием \"" + parameterName + "\" уже существует. Пожалуйста, введите другое название.");
                } else {
                    System.out.println("Введите значение параметра " + parameterName + ":");
                    String parameterValue = scanner.nextLine().trim();
                    additionalParameters.put(parameterName, parameterValue);
                }
            }
        } while (!parameterName.isEmpty());
        return additionalParameters;
    }


    /**
     * Выводит список всех тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void viewWorkouts(User currentUser) {
        if (currentUser.isAdmin()) {
            System.out.println("Все тренировки пользователей:");
            for (User user : userService.getAllUsers()) {
                System.out.println("Тренировки пользователя " + user.getUsername() + ":");
                for (Workout workout : user.getWorkouts()) {
                    System.out.println(workout);
                }
            }
        } else {
            List<Workout> workouts = currentUser.getWorkouts();
            workouts.sort(Comparator.comparing(Workout::getDate));
            System.out.println("Ваши тренировки:");
            for (Workout workout : workouts) {
                System.out.println(workout);
            }
        }
    }

    /**
     * Редактирует существующую тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void editWorkout(User currentUser) {

        int id = InputValidator.promptForPositiveInt("Введите идентификатор тренировки:");

        boolean result = workoutService.checkWorkoutById(currentUser, id);

        if (!result) {
            System.out.println("Тренировка не найдена");
            Logger.log("Редактирование тренировки", currentUser.getUsername(), false);
            return;
        }

        System.out.println("Введите новую дату тренировки (формат гггг-мм-дд). " +
                           "Если параметр остается без изменения, то пропустите ввод, нажав Enter:");
        String inputDate = scanner.nextLine().trim();
        LocalDate date = parseDate(inputDate);

        System.out.println("Введите новый тип тренировки. " +
                           "Если параметр остается без изменения, то пропустите ввод, нажав Enter:");
        String type = null;
        String typeInput = scanner.nextLine().trim();
        if (!typeInput.isEmpty()) {
            type = typeInput;
        }

        System.out.println("Введите новую длительность тренировки (в минутах). " +
                           "Если параметр остается без изменения, то пропустите ввод, нажав Enter:");
        String durationInput = scanner.nextLine().trim();
        int duration = validatePositiveInt(durationInput);

        System.out.println("Введите новое количество потраченных калорий. " +
                           "Если параметр остается без изменения, то пропустите ввод, нажав Enter:");
        String caloriesBurnedInput = scanner.nextLine().trim();
        int caloriesBurned = validatePositiveInt(caloriesBurnedInput);

        workoutService.editWorkout(currentUser, id, date, type, duration, caloriesBurned);
        Logger.log("Редактирование тренировки", currentUser.getUsername(), true);
        System.out.println("Тренировка обновлена");
    }

    /**
     * Удаляет существующую тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void deleteWorkout(User currentUser) {

        System.out.println("Введите идентификатор тренировки:");
        int id = Integer.parseInt(scanner.nextLine());

        boolean result = workoutService.deleteWorkout(currentUser, id);

        if (!result) {
            Logger.log("Удаление тренировки", currentUser.getUsername(), false);
            System.out.println("Тренировка не найдена");
        } else {
            Logger.log("Удаление тренировки", currentUser.getUsername(), true);
            System.out.println("Тренировка удалена");
        }
    }

}
