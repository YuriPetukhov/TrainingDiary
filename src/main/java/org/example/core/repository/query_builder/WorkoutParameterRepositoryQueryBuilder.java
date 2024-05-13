package org.example.core.repository.query_builder;

import org.example.core.domain.WorkoutTypeParameter;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.WorkoutParameterRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutParameterRepositoryQueryBuilder {

    private final WorkoutParameterRepositoryQueriesLoader.WorkoutParameterRepositoryQueries queries;

    public WorkoutParameterRepositoryQueryBuilder() {
        this.queries = WorkoutParameterRepositoryQueriesLoader.loadQueries();
    }
    public Long createSaveQuery(WorkoutTypeParameter workoutTypeParameter) throws SQLException {
        return QueryExecutor.execute(queries.workoutParameterSaveQuery(), workoutTypeParameter.getParameterName(), workoutTypeParameter.getWorkoutTypeId());
    }

    public void createUpdateQuery(WorkoutTypeParameter entity) throws SQLException {
        QueryExecutor.execute(queries.workoutParameterUpdateQuery(), entity.getParameterName());
    }

    public ResultSet createFindByIdQuery(Long id) throws SQLException {
        return QueryExecutor.executeQuery(queries.workoutParameterFindByIdQuery(), id);
    }

    public ResultSet createFindAllQuery() throws SQLException {
        return QueryExecutor.executeQuery(queries.workoutParameterFindAllQuery());
    }

    public ResultSet createFindByNameQuery(String parameterName) throws SQLException {
        return QueryExecutor.executeQuery(queries.workoutParameterFindByNameQuery(), parameterName);
    }

    public void createDeleteQuery(Long id) throws SQLException {
        QueryExecutor.executeDelete(queries.workoutParameterDeleteQuery(), id);
    }

    public ResultSet createFindAllByWorkoutTypeIdQuery(Long id) throws SQLException {
        return QueryExecutor.executeQuery(queries.workoutParameterFindAllByWorkoutTypeId(), id);
    }
}
