package org.example.core.repository.query_loader;

import org.example.config.AppConfig;
import org.example.config.ConfigReader;

import java.util.Map;

public class WorkoutParameterRepositoryQueriesLoader {

    public static record WorkoutParameterRepositoryQueries(
            String workoutParameterSaveQuery,
            String workoutParameterUpdateQuery,
            String workoutParameterFindAllQuery,
            String workoutParameterFindByIdQuery,
            String workoutParameterDeleteQuery,
            String workoutParameterFindByNameQuery,
            String workoutParameterFindAllByWorkoutTypeId
    ) {

    }

    public static WorkoutParameterRepositoryQueries loadQueries() {
        Map<String, String> links = AppConfig.readLinks();
        Map<String, Object> config = ConfigReader.readConfig(links.get("WORKOUT_PARAMETER_REPO_QUERY"));
        Map<String, String> queriesMap = (Map<String, String>) config.get("workout_parameter_repository_query");

        return new WorkoutParameterRepositoryQueriesLoader.WorkoutParameterRepositoryQueries(
                queriesMap.get("WORKOUT_PARAMETER_SAVE_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_UPDATE_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_FIND_ALL_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_FIND_BY_ID_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_DELETE_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_FIND_BY_NAME_QUERY"),
                queriesMap.get("WORKOUT_PARAMETER_FIND_ALL_BY_WORKOUT_TYPE_ID_QUERY")
        );
    }
}
