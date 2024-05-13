package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.ConcreteWorkoutTypeParameter;
import org.example.core.mapper.ConcreteWorkoutParameterMapper;
import org.example.core.mapper.impl.ConcreteWorkoutParameterMapperImpl;
import org.example.core.repository.query_builder.ConcreteWorkoutParameterRepositoryQueryBuilder;
import org.example.core.repository.service_response_builder.ConcreteWorkoutTypeParameterRepository;
import org.example.core.repository.service_response_builder.RepositorySave;
import org.example.core.service.impl.ConcreteWorkoutTypeServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConcreteWorkoutParameterRepositoryImpl implements ConcreteWorkoutTypeParameterRepository,
        RepositorySave<ConcreteWorkoutTypeParameter, Long> {

    private final ConcreteWorkoutParameterRepositoryQueryBuilder queryBuilder = new ConcreteWorkoutParameterRepositoryQueryBuilder();
    private final ConcreteWorkoutParameterMapper concreteWorkoutParameterMapper = new ConcreteWorkoutParameterMapperImpl();
    @Override
    public ConcreteWorkoutTypeParameter save(ConcreteWorkoutTypeParameter concreteWorkoutTypeParameter) {
        return queryBuilder.createSaveQuery(concreteWorkoutTypeParameter);
    }

    public void saveAll(Long id, Map<String, Integer> concreteParameters) throws SQLException {
        queryBuilder.createSaveAllQuery(id, concreteParameters);
    }

    @Override
    public Optional<ConcreteWorkoutTypeParameter> update(ConcreteWorkoutTypeParameter entity) throws SQLException {
        queryBuilder.createUpdateQuery(entity);
        return findById(entity.getId());
    }

    @Override
    public void delete(Long id) {
        queryBuilder.createDeleteQuery(id);
    }

    @Override
    public Optional<ConcreteWorkoutTypeParameter> findById(Long id) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByIdQuery(id);
        if (resultSet.next()) {
            return Optional.of(concreteWorkoutParameterMapper.toConcreteWorkoutTypeParameter(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<ConcreteWorkoutTypeParameter> findAll() throws SQLException {
        ResultSet resultSet = queryBuilder.createFindAllQuery();
        List<ConcreteWorkoutTypeParameter> parameters = new ArrayList<>();
        while (resultSet.next()) {
            ConcreteWorkoutTypeParameter parameter = concreteWorkoutParameterMapper.toConcreteWorkoutTypeParameter(resultSet);
            parameters.add(parameter);
        }
        return parameters;
    }

    public List<ConcreteWorkoutTypeParameter> findByTypeId(Long typeId) throws SQLException {
        ResultSet resultSet = queryBuilder.createByTypeIdQuery(typeId);
        List<ConcreteWorkoutTypeParameter> parameters = new ArrayList<>();
        while (resultSet.next()) {
            ConcreteWorkoutTypeParameter parameter = concreteWorkoutParameterMapper.toConcreteWorkoutTypeParameter(resultSet);
            parameters.add(parameter);
        }
        return parameters;
    }
}
