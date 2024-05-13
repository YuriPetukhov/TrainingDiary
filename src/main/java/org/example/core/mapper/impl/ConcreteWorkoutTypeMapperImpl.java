package org.example.core.mapper.impl;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.mapper.ConcreteWorkoutTypeMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcreteWorkoutTypeMapperImpl implements ConcreteWorkoutTypeMapper {
    @Override
    public ConcreteWorkoutType toConcreteWorkoutType(ResultSet resultSet) throws SQLException {
        ConcreteWorkoutType type = new ConcreteWorkoutType();
        type.setTypeName(resultSet.getString("type_name"));
        type.setWorkoutId(resultSet.getLong("workout_id"));
        type.setId(resultSet.getLong("id"));
        return type;
    }
}
