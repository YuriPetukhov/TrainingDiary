package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.WorkoutTypeParameter;
import org.example.core.repository.query_builder.WorkoutParameterRepositoryQueryBuilder;
import org.example.core.repository.service_response_builder.RepositorySave;
import org.example.core.repository.service_response_builder.WorkoutParameterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutParameterRepositoryImpl implements WorkoutParameterRepository,
        RepositorySave<WorkoutTypeParameter, Long> {

    private final WorkoutParameterRepositoryQueryBuilder queryBuilder = new WorkoutParameterRepositoryQueryBuilder();
    @Override
    public WorkoutTypeParameter save(WorkoutTypeParameter workoutTypeParameter) {
        long workoutTypeId = 0;
        try {
            workoutTypeId = queryBuilder.createSaveQuery(workoutTypeParameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        workoutTypeParameter.setId(workoutTypeId);
        return workoutTypeParameter;
    }

    @Override
    public Optional<WorkoutTypeParameter> update(WorkoutTypeParameter entity) throws SQLException {
        queryBuilder.createUpdateQuery(entity);
        return findById(entity.getId());
    }

    public Optional<WorkoutTypeParameter> findById(Long id) throws SQLException {
        try (ResultSet resultSet = queryBuilder.createFindByIdQuery(id)) {
            if (resultSet.next()) {
                WorkoutTypeParameter workoutTypeParameter = new WorkoutTypeParameter();
                workoutTypeParameter.setId(resultSet.getLong("id"));
                workoutTypeParameter.setParameterName(resultSet.getString("parameter_name"));

                return Optional.of(workoutTypeParameter);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<WorkoutTypeParameter> findAll() throws SQLException {
        List<WorkoutTypeParameter> workoutTypeParameters;
        try (ResultSet resultSet = queryBuilder.createFindAllQuery()) {
            workoutTypeParameters = new ArrayList<>();
            while (resultSet.next()) {
                WorkoutTypeParameter workoutTypeParameter = new WorkoutTypeParameter();
                workoutTypeParameter.setId(resultSet.getLong("id"));
                workoutTypeParameter.setParameterName(resultSet.getString("parameter_name"));
                workoutTypeParameters.add(workoutTypeParameter);
            }
        }
        return workoutTypeParameters;
    }

    @Override
    public Optional<WorkoutTypeParameter> findByName(String parameterName) throws SQLException {
        try (ResultSet resultSet = queryBuilder.createFindByNameQuery(parameterName)) {
            if (resultSet.next()) {
                WorkoutTypeParameter workoutTypeParameter = new WorkoutTypeParameter();
                workoutTypeParameter.setId(resultSet.getLong("id"));
                workoutTypeParameter.setParameterName(resultSet.getString("parameter_name"));

                return Optional.of(workoutTypeParameter);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void delete(Long id) {
        try {
            queryBuilder.createDeleteQuery(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findAllByWorkoutTypeId(Long id) throws SQLException {
        List<String> parametersNames;
        try (ResultSet resultSet = queryBuilder.createFindAllByWorkoutTypeIdQuery(id)) {
            parametersNames = new ArrayList<>();
            while (resultSet.next()) {
                parametersNames.add(resultSet.getString("parameter_name"));
            }
        }
        return parametersNames;
    }
}
