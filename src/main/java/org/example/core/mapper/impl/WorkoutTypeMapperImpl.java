package org.example.core.mapper.impl;

import org.example.core.domain.WorkoutType;
import org.example.core.mapper.WorkoutTypeMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutTypeMapperImpl implements WorkoutTypeMapper {
    @Override
    public WorkoutType toWorkoutType(ResultSet resultSet) throws SQLException {
        WorkoutType workoutType = new WorkoutType();
        workoutType.setId(resultSet.getLong("id"));
        workoutType.setTypeName(resultSet.getString("type_name"));
        workoutType.setUserId(resultSet.getLong("user_id"));
        return workoutType;
    }
}
