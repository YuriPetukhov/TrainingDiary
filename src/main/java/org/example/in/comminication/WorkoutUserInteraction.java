package org.example.in.comminication;

import org.example.core.domain.*;
import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.enums.UserRole;

import org.example.core.service.impl.*;
import org.example.in.util.MenuCreator;
import org.example.in.util.WorkoutInputMenuBuilder;
import org.example.in.util.WorkoutInputMenuCreator;
import org.example.in.validation.InputValidator;
import org.example.logger.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс для взаимодействия с пользователем для работы с тренировками.
 */
public class WorkoutUserInteraction {

    private final MenuCreator menuCreator = new MenuCreator();
    private final WorkoutServiceImpl workoutService = new WorkoutServiceImpl();
    private final InputValidator inputValidator = new InputValidator();
    private final WorkoutTypeServiceImpl workoutTypeService = new WorkoutTypeServiceImpl();
    private final WorkoutParameterServiceImpl workoutParameterService = new WorkoutParameterServiceImpl();
    private final ConcreteWorkoutTypeServiceImpl concreteWorkoutTypeService = new ConcreteWorkoutTypeServiceImpl();
    private final WorkoutInputMenuBuilder workoutInputMenuBuilder = new WorkoutInputMenuBuilder();
    private final WorkoutInputMenuCreator workoutInputMenuCreator = new WorkoutInputMenuCreator();
    private final Logger logger = new Logger();

    /**
     * Добавляет новую тренировку для пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void addWorkout(User currentUser) throws SQLException {

        List<String> userWorkoutTypes = workoutTypeService.getWorkoutTypes(currentUser.getId());
        if (userWorkoutTypes.isEmpty()) {
            System.out.println("У вас пока нет тренировок");
            return;
        }
        int choice = menuCreator.getUserChoose(userWorkoutTypes);

        LocalDate date = inputValidator.promptForDate("Введите дату тренировки (формат гггг-мм-дд):");
        int duration = inputValidator.promptForPositiveInt("Введите длительность тренировки (в минутах):");
        int caloriesBurned = inputValidator.promptForPositiveInt("Введите количество потраченных калорий:");

        Workout newWorkout = workoutService.addWorkout(
                currentUser, date, duration, caloriesBurned, userWorkoutTypes.get(choice - 1));
        if (newWorkout != null) {
            addConcreteParametersToNewWorkout(newWorkout, userWorkoutTypes.get(choice - 1));
        } else {
            System.out.printf("Такой тип \"%s\" уже существует на указанную дату: %s \n", userWorkoutTypes.get(choice - 1), date);
        }
    }

    private void addConcreteParametersToNewWorkout(Workout workout, String workoutTypeName) throws SQLException {
        WorkoutType workoutType = workoutTypeService.findUserWorkoutType(workout.getUserId(), workoutTypeName);
        List<String> workoutTypeParameters = workoutParameterService.findWorkoutParameters(workoutType.getId());
        Map<String, Integer> concreteParameters = new HashMap<>();
        for (String workoutTypeParameter : workoutTypeParameters) {
            int value = inputValidator.promptForPositiveInt(workoutTypeParameter);
            concreteParameters.put(workoutTypeParameter, value);
        }
        concreteWorkoutTypeService.addConcreteWorkoutType(workout, concreteParameters, workoutTypeName);
    }

    /**
     * Добавляет новый тип тренировке.
     */
    public void addNewWorkoutType(User currentUser) {

        String typeName = inputValidator.promptForNonEmptyInput("Введите тип тренировки:");
        WorkoutType newWorkoutType = workoutTypeService.addWorkoutType(currentUser, typeName);
        if (newWorkoutType != null) {
            addParametersToNewWorkoutTyp(newWorkoutType);
        } else {
            System.out.println("Такой тип уже существует");
        }
    }

    /**
     * Добавляет параметры к новому типу тренировки.
     */
    private void addParametersToNewWorkoutTyp(WorkoutType workoutType) {
        Set<String> typeParameters = new HashSet<>();
        while (true) {
            String typeParameter = inputValidator.promptForInput("Введите название параметра (или нажмите Enter, чтобы закончить):");
            if (typeParameter.isEmpty()) {
                break;
            }
            typeParameters.add(typeParameter);
        }
        workoutParameterService.addWorkoutParameters(typeParameters, workoutType);

    }

    /**
     * Выводит список всех тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void viewWorkouts(User currentUser) {
        if (currentUser.getRole().equals(UserRole.ADMIN)) {
            System.out.println("Все тренировки пользователей:");
            logger.log("Получение списка тренировок всех пользователей", currentUser.getUsername(), true);
            Map<String, List<WorkoutDTO>> allWorkouts = workoutService.getAllWorkoutsDTOs();
            for (Map.Entry<String, List<WorkoutDTO>> entry : allWorkouts.entrySet()) {
                String user = entry.getKey();
                System.out.println("Пользователь: " + user);
                for (WorkoutDTO workoutDTO : entry.getValue()) {
                    System.out.println(workoutDTO);
                }
            }
        } else {
            Map<String, List<WorkoutDTO>> workouts = workoutService.findAllDTOsByUser(currentUser);
            logger.log("Получение списка тренировок", currentUser.getUsername(), true);
            for (Map.Entry<String, List<WorkoutDTO>> entry : workouts.entrySet()) {
                System.out.println("Ваши тренировки:");
                for (WorkoutDTO workoutDTO : entry.getValue()) {
                    System.out.println(workoutDTO);
                }
            }

        }
    }

    /**
     * Редактирует существующую тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void editWorkout(User currentUser) {

        Long id = (long) inputValidator.promptForPositiveInt("Введите идентификатор тренировки:");
        WorkoutDTO workout = workoutService.getWorkoutByIdAndUserId(id, currentUser.getId());

        if (workout == null) {
            System.out.println("Тренировка не найдена");
            logger.log("Редактирование тренировки", currentUser.getUsername(), false);
            return;
        }

        Map<String, String> parameters = workoutInputMenuBuilder.getParametersMap(workout);
        Map<String, Integer> newParameters = workoutInputMenuCreator.getNewParameters(parameters);

        workoutService.updateWorkout(workout, newParameters);
        logger.log("Редактирование тренировки", currentUser.getUsername(), true);
        System.out.println("Тренировка обновлена");
    }

    /**
     * Удаляет существующую тренировку пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void deleteWorkout(User currentUser) {

        Long workoutId = (long) inputValidator.promptForPositiveInt("Введите идентификатор тренировки:");

        boolean result = workoutService.deleteWorkout(workoutId, currentUser.getId());

        if (!result) {
            logger.log("Удаление тренировки", currentUser.getUsername(), false);
            System.out.println("Тренировка не найдена");
        } else {
            logger.log("Удаление тренировки", currentUser.getUsername(), true);
            System.out.println("Тренировка удалена");
        }

    }

}
