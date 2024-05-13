package org.example.core.mapper;

import org.example.core.domain.ConcreteWorkoutTypeParameter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConcreteWorkoutParameterMapper {
    ConcreteWorkoutTypeParameter toConcreteWorkoutTypeParameter(ResultSet resultSet) throws SQLException;
}
