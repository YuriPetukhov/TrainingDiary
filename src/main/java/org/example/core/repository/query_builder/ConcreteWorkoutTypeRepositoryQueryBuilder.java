package org.example.core.repository.query_builder;

import org.example.core.domain.ConcreteWorkoutType;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.ConcreteWorkoutTypeRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConcreteWorkoutTypeRepositoryQueryBuilder {

    private final ConcreteWorkoutTypeRepositoryQueriesLoader.ConcreteWorkoutTypeRepositoryQueries queries;

    public ConcreteWorkoutTypeRepositoryQueryBuilder() {
        this.queries = ConcreteWorkoutTypeRepositoryQueriesLoader.loadQueries();
    }

    public Long createSaveQuery(ConcreteWorkoutType concreteWorkoutType)  {
        try {
            return QueryExecutor.execute(queries.concreteTypeSaveQuery(), concreteWorkoutType.getTypeName(), concreteWorkoutType.getWorkoutId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ConcreteWorkoutType> createUpdateQuery(ConcreteWorkoutType entity) {
        return Optional.empty();
    }

    public void createDeleteQuery(Long id) {
        try {
            QueryExecutor.executeDelete(queries.concreteTypeDeleteQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdQuery(Long id) {
        ResultSet resultSet = null;
        try {
            resultSet = QueryExecutor.executeQuery(queries.concreteTypeFindByIdQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet createFindAllQuery() throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = QueryExecutor.executeQuery(queries.concreteTypeFindAllQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
