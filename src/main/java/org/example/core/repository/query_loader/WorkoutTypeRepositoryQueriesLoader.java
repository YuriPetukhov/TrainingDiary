package org.example.core.repository.query_loader;

import org.example.config.AppConfig;
import org.example.config.ConfigReader;

import java.util.Map;

public class WorkoutTypeRepositoryQueriesLoader {

    public static record WorkoutTypeRepositoryQueries(
            String workoutTypeSaveQuery,
            String workoutTypeUpdateQuery,
            String workoutTypeFindAllQuery,
            String workoutTypeFindByIdQuery,
            String workoutTypeDeleteQuery,
            String workoutTypeIsWorkoutTypeWithNameAndUserIdExists,
            String workoutTypeFindTypeNamesByUserId,
            String workoutTypeFindByNameAndUserId,
            String workoutTypeFindByIdAndUserId
    ) {

    }

    public static WorkoutTypeRepositoryQueriesLoader.WorkoutTypeRepositoryQueries loadQueries() {
        Map<String, String> links = AppConfig.readLinks();
        Map<String, Object> config = ConfigReader.readConfig(links.get("WORKOUT_TYPE_REPO_QUERY"));
        Map<String, String> queriesMap = (Map<String, String>) config.get("workout_type_repository_query");

        return new WorkoutTypeRepositoryQueriesLoader.WorkoutTypeRepositoryQueries(
                queriesMap.get("WORKOUT_TYPE_SAVE_QUERY"),
                queriesMap.get("WORKOUT_TYPE_UPDATE_QUERY"),
                queriesMap.get("WORKOUT_TYPE_FIND_ALL_QUERY"),
                queriesMap.get("WORKOUT_TYPE_FIND_BY_ID_QUERY"),
                queriesMap.get("WORKOUT_TYPE_DELETE_QUERY"),
                queriesMap.get("WORKOUT_TYPE_IS_WORKOUT_TYPE_WITH_NAME_AND_USER_ID_EXISTS"),
                queriesMap.get("WORKOUT_TYPE_FIND_TYPE_NAMES_BY_USER_ID"),
                queriesMap.get("WORKOUT_TYPE_FIND_BY_NAME_AND_USER_ID"),
                queriesMap.get("WORKOUT_TYPE_FIND_BY_ID_AND_USER_ID")
        );
    }
}
