package org.example.core.repository.query_loader;

import org.example.config.ConfigReader;

import java.util.Map;

import static org.example.config.AppConfig.CONCRETE_WORKOUT_PARAMETER_REPO_QUERY;
public class ConcreteWorkoutParameterRepositoryQueriesLoader {
    public static record ConcreteWorkoutParameterRepositoryQueries(
            String concreteParameterSaveQuery,
            String concreteParameterSaveAllQuery,
            String concreteParameterUpdateQuery,
            String concreteParameterFindAllQuery,
            String concreteParameterFindByIdQuery,
            String concreteParameterFindByTypeIdQuery,
            String concreteParameterDeleteQuery
    ) {

    }

    public static ConcreteWorkoutParameterRepositoryQueriesLoader.ConcreteWorkoutParameterRepositoryQueries loadQueries() {
        Map<String, Object> config = ConfigReader.readConfig(CONCRETE_WORKOUT_PARAMETER_REPO_QUERY);
        Map<String, String> queriesMap = (Map<String, String>) config.get("concrete_workout_parameter_repository_query");

        return new ConcreteWorkoutParameterRepositoryQueriesLoader.ConcreteWorkoutParameterRepositoryQueries(
                queriesMap.get("CONCRETE_PARAMETER_SAVE_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_SAVE_ALL_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_UPDATE_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_FIND_ALL_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_FIND_BY_ID_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_FIND_BY_TYPE_ID_QUERY"),
                queriesMap.get("CONCRETE_PARAMETER_DELETE_QUERY")
        );
    }
}
