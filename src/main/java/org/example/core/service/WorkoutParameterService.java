package org.example.core.service;

import org.example.core.domain.WorkoutTypeParameter;

import java.util.List;

public interface WorkoutParameterService {
    WorkoutTypeParameter addWorkoutParameter(String parameterName, Long workoutTypeId);
    void updateWorkoutParameter(WorkoutTypeParameter parameter);
    void deleteWorkoutParameter(Long id);

    WorkoutTypeParameter getWorkoutParameterById(Long id);
}
