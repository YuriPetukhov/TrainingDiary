package org.example.core.repository.query_loader;

import org.example.config.ConfigReader;

import java.util.Map;

import static org.example.config.AppConfig.CONCRETE_WORKOUT_TYPE_REPO_QUERY;

public class ConcreteWorkoutTypeRepositoryQueriesLoader {

    public static record ConcreteWorkoutTypeRepositoryQueries(
            String concreteTypeSaveQuery,
            String concreteTypeUpdateQuery,
            String concreteTypeFindAllQuery,
            String concreteTypeFindByIdQuery,
            String concreteTypeDeleteQuery
    ) {

    }

    public static ConcreteWorkoutTypeRepositoryQueriesLoader.ConcreteWorkoutTypeRepositoryQueries loadQueries() {
        Map<String, Object> config = ConfigReader.readConfig(CONCRETE_WORKOUT_TYPE_REPO_QUERY);
        Map<String, String> queriesMap = (Map<String, String>) config.get("concrete_workout_type_repository_query");

        return new ConcreteWorkoutTypeRepositoryQueriesLoader.ConcreteWorkoutTypeRepositoryQueries(
                queriesMap.get("CONCRETE_WORKOUT_TYPE_SAVE_QUERY"),
                queriesMap.get("CONCRETE_WORKOUT_TYPE_UPDATE_QUERY"),
                queriesMap.get("CONCRETE_WORKOUT_TYPE_FIND_ALL_QUERY"),
                queriesMap.get("CONCRETE_WORKOUT_TYPE_FIND_BY_ID_QUERY"),
                queriesMap.get("CONCRETE_WORKOUT_TYPE_DELETE_QUERY")
        );
    }
}