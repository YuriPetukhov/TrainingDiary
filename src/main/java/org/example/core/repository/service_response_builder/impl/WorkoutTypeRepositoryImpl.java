package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.WorkoutType;
import org.example.core.repository.query_builder.WorkoutTypeRepositoryQueryBuilder;
import org.example.core.repository.service_response_builder.RepositorySave;
import org.example.core.repository.service_response_builder.WorkoutTypeRepository;
import org.example.core.mapper.impl.WorkoutTypeMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutTypeRepositoryImpl implements WorkoutTypeRepository,
        RepositorySave<WorkoutType, Long> {

    private final WorkoutTypeMapperImpl workoutTypeMapper = new WorkoutTypeMapperImpl();
    private final WorkoutTypeRepositoryQueryBuilder queryBuilder = new WorkoutTypeRepositoryQueryBuilder();
    @Override
    public WorkoutType save(WorkoutType workoutType) {
        long workoutTypeId = queryBuilder.createSaveQuery(workoutType);
        workoutType.setId(workoutTypeId);
        return workoutType;
    }

    @Override
    public Optional<WorkoutType> update(WorkoutType entity) {
        queryBuilder.createUpdateQuery(entity);
        try {
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        queryBuilder.createDeleteQuery(id);
    }

    public Optional<WorkoutType> findById(Long id) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByIdQuery(id);
        if (resultSet.next()) {
            return Optional.of(workoutTypeMapper.toWorkoutType(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<WorkoutType> findAll() throws SQLException {
        ResultSet resultSet = queryBuilder.createFindAllQuery();
        List<WorkoutType> workoutTypes = new ArrayList<>();
        while (resultSet.next()) {
            WorkoutType workoutType = workoutTypeMapper.toWorkoutType(resultSet);
            workoutTypes.add(workoutType);
        }
        return workoutTypes;
    }

    public boolean isWorkTypeWithNameAndUserIdExists(Long id, String typeName) {
        return queryBuilder.createIsWorkTypeWithNameAndUserIdExistsQuery(id, typeName);
    }

    public List<String> findTypeNamesByUserId(Long id) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindTypeNamesByUserIdQuery(id);
        List<String> typeNames = new ArrayList<>();
        while (resultSet.next()) {
            typeNames.add(resultSet.getString("type_name"));
        }
        return typeNames;
    }


    public WorkoutType findByNameAndUserId(Long id, String typeName) {
        try (ResultSet resultSet = queryBuilder.createFindByNameAndUserIdQuery(id, typeName)) {
            if (resultSet.next()) {
                return workoutTypeMapper.toWorkoutType(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<WorkoutType> findByIdAndUserId(Long typeId, Long userId) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByIdAndUserIdQuery(typeId, userId);
        if (resultSet.next()) {
            return Optional.of(workoutTypeMapper.toWorkoutType(resultSet));
        } else {
            return Optional.empty();
        }
    }
}
