package org.example.core.mapper;

import org.example.core.domain.WorkoutType;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface WorkoutTypeMapper {
    WorkoutType toWorkoutType(ResultSet resultSet) throws SQLException;
}
