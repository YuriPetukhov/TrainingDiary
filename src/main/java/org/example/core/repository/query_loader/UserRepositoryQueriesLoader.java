package org.example.core.repository.query_loader;

import org.example.config.ConfigReader;

import java.util.Map;

import static org.example.config.AppConfig.USER_REPO_QUERY;

public class UserRepositoryQueriesLoader {
    public static record UserRepositoryQueries(
            String userSaveQuery,
            String userUpdateQuery,
            String userFindAllQuery,
            String userFindByIdQuery,
            String userDeleteQuery,
            String userFindByNameQuery
    ) {

    }

    public static UserRepositoryQueries loadQueries() {
        Map<String, Object> config = ConfigReader.readConfig(USER_REPO_QUERY);
        Map<String, String> queriesMap = (Map<String, String>) config.get("user_repository_query");

        return new UserRepositoryQueries(
                queriesMap.get("USER_SAVE_QUERY"),
                queriesMap.get("USER_UPDATE_QUERY"),
                queriesMap.get("USER_FIND_ALL_QUERY"),
                queriesMap.get("USER_FIND_BY_ID_QUERY"),
                queriesMap.get("USER_DELETE_QUERY"),
                queriesMap.get("USER_FIND_BY_NAME_QUERY")
        );
    }
}
