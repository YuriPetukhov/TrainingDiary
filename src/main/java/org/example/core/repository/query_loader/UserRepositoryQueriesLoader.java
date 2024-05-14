package org.example.core.repository.query_loader;

import org.example.config.AppConfig;
import org.example.config.ConfigReader;

import java.util.Map;
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
        Map<String, String> links = AppConfig.readLinks();
        Map<String, Object> config = ConfigReader.readConfig(links.get("USER_REPO_QUERY"));
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
