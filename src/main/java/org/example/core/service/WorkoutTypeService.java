package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.WorkoutType;

import java.util.List;

public interface WorkoutTypeService {
    WorkoutType addWorkoutType(User currentUser, String typeName);

    WorkoutType findByIdAndUserId(Long typeId, Long userId);
}
