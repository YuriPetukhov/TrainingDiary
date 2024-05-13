package org.example.core.mapper.impl;

import org.example.core.domain.ConcreteWorkoutTypeParameter;
import org.example.core.mapper.ConcreteWorkoutParameterMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcreteWorkoutParameterMapperImpl implements ConcreteWorkoutParameterMapper {

    @Override
    public ConcreteWorkoutTypeParameter toConcreteWorkoutTypeParameter(ResultSet resultSet) throws SQLException {
        ConcreteWorkoutTypeParameter parameter = new ConcreteWorkoutTypeParameter();
        parameter.setId(resultSet.getLong("id"));
        parameter.setConcreteWorkoutTypeId(resultSet.getLong("concrete_workout_type_id"));
        parameter.setValue(resultSet.getInt("value"));
        parameter.setParameterName(resultSet.getString("parameter_name"));
        return parameter;
    }
}
