package org.example.core.repository.query_builder;

import org.example.core.domain.Log;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.LogRepositoryQueriesLoader;

import java.sql.SQLException;

public class LogRepositoryQueryBuilder {
    private final LogRepositoryQueriesLoader.LogRepositoryQueries queries;

    public LogRepositoryQueryBuilder() {
        this.queries = LogRepositoryQueriesLoader.loadQueries();
    }

    public Long createSaveQuery(Log entity) {
        try {
            return QueryExecutor.execute(queries.logSaveQuery(), entity.getAction(), entity.getUser(), entity.getTimestamp(), entity.isSuccess());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
