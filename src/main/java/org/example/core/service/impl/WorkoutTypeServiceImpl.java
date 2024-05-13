package org.example.core.service.impl;

import org.example.core.domain.User;
import org.example.core.domain.WorkoutType;
import org.example.core.repository.service_response_builder.impl.WorkoutTypeRepositoryImpl;
import org.example.core.service.WorkoutTypeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class WorkoutTypeServiceImpl implements WorkoutTypeService {

    private final WorkoutTypeRepositoryImpl workoutTypeRepositoryImpl = new WorkoutTypeRepositoryImpl();
    @Override
    public WorkoutType addWorkoutType(User currentUser, String typeName) {

        if (isWorkTypeWithNameAndUserIdExists(currentUser.getId(), typeName)) {
            return null;
        }

        WorkoutType newWorkoutType = new WorkoutType();
        newWorkoutType.setTypeName(typeName);
        newWorkoutType.setUserId(currentUser.getId());
        WorkoutType savedWorkoutType = null;
        savedWorkoutType = workoutTypeRepositoryImpl.save(newWorkoutType);

        return savedWorkoutType;
    }

    @Override
    public WorkoutType findByIdAndUserId(Long typeId, Long userId) {
        Optional<WorkoutType> optType;
        try {
            optType = workoutTypeRepositoryImpl.findByIdAndUserId(typeId, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optType.orElse(null);
    }

    private boolean isWorkTypeWithNameAndUserIdExists(Long id, String typeName) {
        return workoutTypeRepositoryImpl.isWorkTypeWithNameAndUserIdExists(id, typeName);
    }

    public List<String> getWorkoutTypes(Long id) throws SQLException {
        return workoutTypeRepositoryImpl.findTypeNamesByUserId(id);
    }


    public WorkoutType findUserWorkoutType(Long id, String typeName) {
        return workoutTypeRepositoryImpl.findByNameAndUserId(id, typeName);
    }
}
