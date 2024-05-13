package org.example.core.service.impl;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.domain.Workout;
import org.example.core.repository.service_response_builder.ConcreteWorkoutTypeRepository;
import org.example.core.repository.service_response_builder.impl.ConcreteWorkoutTypeRepositoryImpl;
import org.example.core.service.WorkoutService;

import java.sql.SQLException;
import java.util.Map;

public class ConcreteWorkoutTypeServiceImpl {
    private final ConcreteWorkoutTypeRepository concreteWorkoutTypeRepository = new ConcreteWorkoutTypeRepositoryImpl();
    private final ConcreteWorkoutParameterServiceImpl concreteWorkoutTypeParameterService = new ConcreteWorkoutParameterServiceImpl();
    private final WorkoutService workoutService = new WorkoutServiceImpl();
    public void addConcreteWorkoutType(Workout workout, Map<String, Integer> concreteParameters, String typeName) throws SQLException {
        ConcreteWorkoutType concreteWorkoutType = new ConcreteWorkoutType();
        concreteWorkoutType.setTypeName(typeName);
        concreteWorkoutType.setWorkoutId(workout.getId());
        ConcreteWorkoutType savedConcreteWorkoutType = concreteWorkoutTypeRepository.save(concreteWorkoutType);
        workout.setConcreteWorkoutTypeId(savedConcreteWorkoutType.getId());
        workoutService.setConcreteWorkout(workout);
        concreteWorkoutTypeParameterService.addConcreteWorkoutTypeParameters(savedConcreteWorkoutType.getId(), concreteParameters);
    }

    public void deleteConcreteWorkoutType(Long concreteWorkoutTypeId) {
        try {
            concreteWorkoutTypeRepository.delete(concreteWorkoutTypeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
