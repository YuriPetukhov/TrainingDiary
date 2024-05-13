package org.example.core.mapper;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.domain.ConcreteWorkoutTypeParameter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConcreteWorkoutTypeMapper {
    ConcreteWorkoutType toConcreteWorkoutType(ResultSet resultSet) throws SQLException;
}
