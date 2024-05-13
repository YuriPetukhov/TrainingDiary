package org.example.core.service.impl;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.domain.WorkoutType;
import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.repository.service_response_builder.impl.WorkoutRepositoryImpl;
import org.example.core.service.WorkoutService;
import org.example.core.service.WorkoutTypeService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с тренировками.
 */
public class WorkoutServiceImpl implements WorkoutService {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final WorkoutRepositoryImpl workoutRepositoryImpl = new WorkoutRepositoryImpl();
    private final ConcreteWorkoutParameterServiceImpl concreteWorkoutParameterService = new ConcreteWorkoutParameterServiceImpl();
    private final WorkoutTypeService workoutTypeService = new WorkoutTypeServiceImpl();

    /**
     * Добавляет тренировку пользователю.
     *
     * @param currentUser    текущий пользователь
     * @param date           дата тренировки
     * @param workoutType    тип тренировки
     * @param duration       продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     */

    @Override
    public Workout addWorkout(User currentUser, LocalDate date, int duration, int caloriesBurned, String workoutType) {

        boolean result = workoutRepositoryImpl.isWorkTypeWithNameAndUserIdAndDateExists(
                currentUser.getId(), date, workoutType);
        if (result) {
            return null;
        }
        Workout workout = new Workout();
        workout.setCaloriesBurned(caloriesBurned);
        workout.setDate(date);
        workout.setDuration(duration);
        workout.setUserId(currentUser.getId());
        workout = workoutRepositoryImpl.save(workout);

        return workout;
    }


    /**
     * Получает общее количество сожженных калорий пользователем.
     *
     * @param currentUser текущий пользователь
     * @return общее количество сожженных калорий
     */
    @Override
    public int getCaloriesBurnedTotal(User currentUser) {
        try {
            return workoutRepositoryImpl.getTotalCaloriesBurnedByUserId(currentUser.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получает статистику сожженных калорий по датам для пользователя.
     *
     * @param currentUser текущий пользователь
     * @return статистика сожженных калорий по датам
     */
    @Override
    public Map<String, Integer> getCaloriesBurnedByDate(User currentUser) {
        try {
            return workoutRepositoryImpl.getCaloriesBurnedByDate(currentUser.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Получает общую продолжительность тренировок пользователя.
     *
     * @param currentUser текущий пользователь
     * @return общая продолжительность тренировок в минутах
     */
    @Override
    public int getTotalDuration(User currentUser) {
        try {
            return workoutRepositoryImpl.getTotalDurationByUserId(currentUser.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получает отфильтрованный список тренировок.
     *
     * @param startDate начальная дата диапазона фильтрации
     * @param endDate   конечная дата диапазона фильтрации
     * @param typeId    тип тренировки для фильтрации
     * @return отфильтрованный список тренировок
     */
    @Override
    public Map<String, List<WorkoutDTO>> getFilteredWorkouts(Long userId, LocalDate startDate, LocalDate endDate, Long typeId) {
        WorkoutType type= workoutTypeService.findByIdAndUserId(typeId, userId);
        return workoutRepositoryImpl.getFilteredWorkouts(userId, startDate, endDate, type.getTypeName());
    }

    @Override
    public void updateWorkout(WorkoutDTO workout, Map<String, Integer> newParameters) {
        Workout existingWorkout = getWorkoutById(workout.getWorkoutId());

        existingWorkout.setDuration(newParameters.get("duration"));
        existingWorkout.setCaloriesBurned(newParameters.get("caloriesBurned"));

        Long typeId = existingWorkout.getConcreteWorkoutTypeId();
        try {
            concreteWorkoutParameterService.updateConcreteParameterValues(typeId, newParameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        workoutRepositoryImpl.save(existingWorkout);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return null;
    }

    public Map<String, List<WorkoutDTO>> getAllWorkoutsDTOs() {
        return workoutRepositoryImpl.findAllDTOs();
    }

    public Map<String, List<WorkoutDTO>> findAllDTOsByUser(User currentUser) {
        return workoutRepositoryImpl.findAllDTOsByUser(currentUser);
    }

    @Override
    public Workout getWorkoutById(Long id) {
        Optional<Workout> optionalWorkout = null;
        try {
            optionalWorkout = workoutRepositoryImpl.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalWorkout.orElse(null);
    }

    @Override
    public void setConcreteWorkout(Workout workout) {
        workoutRepositoryImpl.createUpdateConcreteWorkoutTypeIdQuery(workout);
    }

    @Override
    public WorkoutDTO getWorkoutByIdAndUserId(Long workoutId, Long userId) {
        Optional<WorkoutDTO> optionalWorkout = workoutRepositoryImpl.getWorkoutByIdAndUserId(workoutId, userId);
        return optionalWorkout.orElse(null);
    }

    @Override
    public boolean deleteWorkout(Long workoutId, Long userId) {
        Optional<Workout> optWorkout = Optional.empty();
        try {
            optWorkout = workoutRepositoryImpl.findById(workoutId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (optWorkout.isPresent()) {
            concreteWorkoutParameterService.deleteParameters(optWorkout.get().getConcreteWorkoutTypeId());
            workoutRepositoryImpl.deleteWorkout(workoutId, userId, optWorkout.get().getConcreteWorkoutTypeId());
            return true;
        } else {
            return false;
        }
    }

}
