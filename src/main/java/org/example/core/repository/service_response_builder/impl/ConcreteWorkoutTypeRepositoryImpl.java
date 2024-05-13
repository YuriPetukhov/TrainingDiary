package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.mapper.ConcreteWorkoutTypeMapper;
import org.example.core.mapper.impl.ConcreteWorkoutTypeMapperImpl;
import org.example.core.repository.query_builder.ConcreteWorkoutTypeRepositoryQueryBuilder;
import org.example.core.repository.service_response_builder.ConcreteWorkoutTypeRepository;
import org.example.core.repository.service_response_builder.RepositorySave;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteWorkoutTypeRepositoryImpl implements ConcreteWorkoutTypeRepository,
        RepositorySave<ConcreteWorkoutType, Long> {

    private final ConcreteWorkoutTypeRepositoryQueryBuilder queryBuilder = new ConcreteWorkoutTypeRepositoryQueryBuilder();
    private final ConcreteWorkoutTypeMapper concreteWorkoutTypeMapper = new ConcreteWorkoutTypeMapperImpl();
    @Override
    public ConcreteWorkoutType save(ConcreteWorkoutType concreteWorkoutType)  {
        long concreteWorkoutTypeId = queryBuilder.createSaveQuery(concreteWorkoutType);
        concreteWorkoutType.setId(concreteWorkoutTypeId);
        return concreteWorkoutType;
    }

    @Override
    public Optional<ConcreteWorkoutType> update(ConcreteWorkoutType entity) {
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

    @Override
    public Optional<ConcreteWorkoutType> findById(Long id) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByIdQuery(id);
        if (resultSet.next()) {
            return Optional.of(concreteWorkoutTypeMapper.toConcreteWorkoutType(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<ConcreteWorkoutType> findAll() throws SQLException {
        ResultSet resultSet = queryBuilder.createFindAllQuery();
        List<ConcreteWorkoutType> types = new ArrayList<>();
        while (resultSet.next()) {
            ConcreteWorkoutType type = concreteWorkoutTypeMapper.toConcreteWorkoutType(resultSet);
            types.add(type);
        }
        return types;
    }

}
