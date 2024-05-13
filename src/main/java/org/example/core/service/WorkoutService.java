package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WorkoutService {

    Workout addWorkout(User currentUser, LocalDate date, int duration, int caloriesBurned, String workoutType);


    int getCaloriesBurnedTotal(User currentUser);

    Map<String, Integer> getCaloriesBurnedByDate(User currentUser);

    int getTotalDuration(User currentUser);

    Map<String, List<WorkoutDTO>> getFilteredWorkouts(Long userId, LocalDate startDate, LocalDate endDate, Long typeId);

    void updateWorkout(WorkoutDTO workout, Map<String, Integer> newParameters) throws SQLException;

    List<Workout> getAllWorkouts();

    Workout getWorkoutById(Long id);

    void setConcreteWorkout(Workout workout) throws SQLException;

    WorkoutDTO getWorkoutByIdAndUserId(Long id, Long id1);

    boolean deleteWorkout(Long workoutId, Long userId);
}
