package org.example.core.repository.query_loader;

import org.example.config.AppConfig;
import org.example.config.ConfigReader;

import java.util.Map;

public class WorkoutRepositoryQueriesLoader {

    public static record WorkoutRepositoryQueries(
            String workoutInsertQuery,
            String workoutUpdateQuery,
            String workoutFindAllQuery,
            String workoutFindByIdQuery,
            String workoutFindAllDTOsQuery,
            String workoutFindAllDTOsByUserQuery,
            String workoutIsWorkTypeWithNameAndUserIdAndDateExistsQuery,
            String workoutFindByIdAndUserIdQuery,
            String workoutUpdateConcreteWorkoutTypeIdQuery,
            String workoutDeleteQuery,
            String totalDurationByUserId,
            String totalCaloriesBurnedByUserId,
            String caloriesBurnedByDate,
            String filteredWorkouts
    ) {

    }

    public static WorkoutRepositoryQueries loadQueries() {
        Map<String, String> links = AppConfig.readLinks();
        Map<String, Object> config = ConfigReader.readConfig(links.get("WORKOUT_REPO_QUERY"));
        Map<String, String> queriesMap = (Map<String, String>) config.get("workout_repository_query");

        return new WorkoutRepositoryQueriesLoader.WorkoutRepositoryQueries(
                queriesMap.get("WORKOUT_INSERT_QUERY"),
                queriesMap.get("WORKOUT_UPDATE_QUERY"),
                queriesMap.get("WORKOUT_FIND_ALL_QUERY"),
                queriesMap.get("WORKOUT_FIND_BY_ID_QUERY"),
                queriesMap.get("WORKOUT_FIND_ALL_DTOS_QUERY"),
                queriesMap.get("WORKOUT_FIND_ALL_DTOS_BY_USER_QUERY"),
                queriesMap.get("WORKOUT_IS_WORKOUT_TYPE_WITH_NAME_AND_USER_ID_AND_DATE_EXISTS_QUERY"),
                queriesMap.get("WORKOUT_FIND_BY_USER_ID_AND_WORKOUT_ID_QUERY"),
                queriesMap.get("WORKOUT_UPDATE_CONCRETE_WORKOUT_TYPE_ID_QUERY"),
                queriesMap.get("WORKOUT_DELETE_QUERY"),
                queriesMap.get("GET_TOTAL_DURATION_QUERY"),
                queriesMap.get("GET_TOTAL_CALORIES_BURNED_QUERY"),
                queriesMap.get("GET_TOTAL_CALORIES_BURNED_BY_DATE_QUERY"),
                queriesMap.get("GET_FILTERED_WORKOUTS_QUERY")
        );
    }
}
