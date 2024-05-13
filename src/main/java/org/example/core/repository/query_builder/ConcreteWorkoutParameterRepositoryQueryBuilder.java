package org.example.core.repository.query_builder;

import org.example.core.domain.ConcreteWorkoutTypeParameter;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.ConcreteWorkoutParameterRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ConcreteWorkoutParameterRepositoryQueryBuilder {

    private final ConcreteWorkoutParameterRepositoryQueriesLoader.ConcreteWorkoutParameterRepositoryQueries queries;

    public ConcreteWorkoutParameterRepositoryQueryBuilder() {
        this.queries = ConcreteWorkoutParameterRepositoryQueriesLoader.loadQueries();
    }

    public ConcreteWorkoutTypeParameter createSaveQuery(ConcreteWorkoutTypeParameter concreteWorkoutTypeParameter) {
        try {
            QueryExecutor.execute(queries.concreteParameterSaveQuery(), concreteWorkoutTypeParameter.getParameterName(), concreteWorkoutTypeParameter.getValue(), concreteWorkoutTypeParameter.getConcreteWorkoutTypeId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return concreteWorkoutTypeParameter;
    }

    public void createSaveAllQuery(Long id, Map<String, Integer> concreteParameters) throws SQLException {
        StringBuilder query = new StringBuilder(queries.concreteParameterSaveAllQuery());
        for (Map.Entry<String, Integer> entry : concreteParameters.entrySet()) {
            query.append("('").append(entry.getKey()).append("', ").append(entry.getValue()).append(", ").append(id).append("), ");
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        QueryExecutor.executeDelete(query.toString());
    }

    public void createUpdateQuery(ConcreteWorkoutTypeParameter entity) {
        try {
            QueryExecutor.executeUpdate(queries.concreteParameterUpdateQuery(), entity.getValue(), entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDeleteQuery(Long id) {
        try {
            QueryExecutor.executeDelete(queries.concreteParameterDeleteQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdQuery(Long id) {
        try {
            return QueryExecutor.executeQuery(queries.concreteParameterFindByIdQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllQuery() {
        try {
            return QueryExecutor.executeQuery(queries.concreteParameterFindAllQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createByTypeIdQuery(Long typeId) {
        try {
            return QueryExecutor.executeQuery(queries.concreteParameterFindByTypeIdQuery(), typeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
