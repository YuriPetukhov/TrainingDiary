package org.example.core.repository.query_loader;

import org.example.config.ConfigReader;

import java.util.Map;

import static org.example.config.AppConfig.LOG_REPO_QUERY;
public class LogRepositoryQueriesLoader {

    public static record LogRepositoryQueries(
            String logSaveQuery
    ) {

    }

    public static LogRepositoryQueriesLoader.LogRepositoryQueries loadQueries() {
        Map<String, Object> config = ConfigReader.readConfig(LOG_REPO_QUERY);
        Map<String, String> queriesMap = (Map<String, String>) config.get("log_repository_query");

        return new LogRepositoryQueriesLoader.LogRepositoryQueries(
                queriesMap.get("LOG_SAVE_QUERY")
        );
    }
}
