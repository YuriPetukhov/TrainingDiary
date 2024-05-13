package org.example.core.repository.query_builder;

import org.example.core.domain.User;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.UserRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryQueryBuilder {

    private final UserRepositoryQueriesLoader.UserRepositoryQueries queries;

    public UserRepositoryQueryBuilder() {
        this.queries = UserRepositoryQueriesLoader.loadQueries();
    }
    public Long createSaveQuery(User user) {
        try {
            return QueryExecutor.execute(queries.userSaveQuery(), user.getUsername(), user.getPassword(), user.getRole().name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUpdateQuery(User user) {
        try {
            QueryExecutor.execute(queries.userUpdateQuery(), user.getUsername(), user.getPassword(), user.getRole().name(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllQuery() {
        try {
            return QueryExecutor.executeQuery(queries.userFindAllQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdQuery(Long id) {
        try {
            return QueryExecutor.executeQuery(queries.userFindByIdQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createDeleteQuery(Long id) {
        try {
            QueryExecutor.executeDelete(queries.userDeleteQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet createFindByNameQuery(String username) {
        try {
            return QueryExecutor.executeQuery(queries.userFindByNameQuery(), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
