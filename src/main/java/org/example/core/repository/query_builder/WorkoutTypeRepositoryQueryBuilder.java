package org.example.core.repository.query_builder;

import org.example.core.domain.WorkoutType;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.WorkoutTypeRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutTypeRepositoryQueryBuilder {

    private final WorkoutTypeRepositoryQueriesLoader.WorkoutTypeRepositoryQueries queries;

    public WorkoutTypeRepositoryQueryBuilder() {
        this.queries = WorkoutTypeRepositoryQueriesLoader.loadQueries();
    }

    public Long createSaveQuery(WorkoutType workoutType) {
        try {
            return QueryExecutor.execute(queries.workoutTypeSaveQuery(), workoutType.getTypeName(), workoutType.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUpdateQuery(WorkoutType entity) {
        try {
            QueryExecutor.executeQuery(queries.workoutTypeUpdateQuery(), entity.getTypeName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDeleteQuery(Long id) {
        try {
            QueryExecutor.executeDelete(queries.workoutTypeDeleteQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdQuery(Long id) {
        try {
            return QueryExecutor.executeQuery(queries.workoutTypeFindByIdQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllQuery() {
        try {
            return QueryExecutor.executeQuery(queries.workoutTypeFindAllQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createIsWorkTypeWithNameAndUserIdExistsQuery(Long id, String typeName) {
        try (ResultSet resultSet = QueryExecutor.executeQuery(queries.workoutTypeIsWorkoutTypeWithNameAndUserIdExists(), typeName, id)) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindTypeNamesByUserIdQuery(Long id) {
        try {
            return QueryExecutor.executeQuery(queries.workoutTypeFindTypeNamesByUserId(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet createFindByNameAndUserIdQuery(Long id, String typeName) {
        try {
            return QueryExecutor.executeQuery(queries.workoutTypeFindByNameAndUserId(), typeName, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdAndUserIdQuery(Long typeId, Long userId) {
        try {
            return QueryExecutor.executeQuery(queries.workoutTypeFindByIdAndUserId(), typeId, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
