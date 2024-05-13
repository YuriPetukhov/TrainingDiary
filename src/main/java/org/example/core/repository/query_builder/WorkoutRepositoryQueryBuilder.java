package org.example.core.repository.query_builder;

import org.example.core.domain.Workout;
import org.example.core.repository.query_executor.QueryExecutor;
import org.example.core.repository.query_loader.WorkoutRepositoryQueriesLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class WorkoutRepositoryQueryBuilder {

    private final WorkoutRepositoryQueriesLoader.WorkoutRepositoryQueries queries;

    public WorkoutRepositoryQueryBuilder() {
        this.queries = WorkoutRepositoryQueriesLoader.loadQueries();
    }

    public Long createInsertQuery(Workout workout) {
        try {
            return QueryExecutor.execute(queries.workoutInsertQuery(), workout.getDate(), workout.getDuration(), workout.getCaloriesBurned(), workout.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createUpdateQuery(Workout workout) {
        try {
            return QueryExecutor.executeUpdate(queries.workoutUpdateQuery(), workout.getDuration(), workout.getCaloriesBurned(), workout.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUpdateConcreteWorkoutTypeIdQuery(Long workoutId, Long concreteWorkoutTypeId) {
        try {
            QueryExecutor.executeUpdate(queries.workoutUpdateConcreteWorkoutTypeIdQuery(), concreteWorkoutTypeId, workoutId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Workout> update(Workout entity) {
        return Optional.empty();
    }

    public boolean createDeleteQuery(Long workoutId, Long userId) {
        try {
            return QueryExecutor.executeDelete(queries.workoutDeleteQuery(), workoutId, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindByIdQuery(Long id) {
        try {
            return QueryExecutor.executeQuery(queries.workoutFindByIdQuery(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllQuery() {
        try {
            return QueryExecutor.executeQuery(queries.workoutFindAllQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllDTOsQuery() {
        try {
            return QueryExecutor.executeQuery(queries.workoutFindAllDTOsQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createFindAllDTOsByUserQuery(Long userId) {
        try {
            return QueryExecutor.executeQuery(queries.workoutFindAllDTOsByUserQuery(), userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Workout> findByName(String name) {
        return Optional.empty();
    }

    public ResultSet createIsWorkTypeWithNameAndUserIdAndDateExistsQuery(Long id, LocalDate date, String workoutType) {
        try {
            return QueryExecutor.executeQuery(queries.workoutIsWorkTypeWithNameAndUserIdAndDateExistsQuery(), date, id, workoutType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ResultSet> createFindUserWorkoutQuery(Long workoutId, Long userId) {
        try {
            return Optional.of(QueryExecutor.executeQuery(queries.workoutFindByIdAndUserIdQuery(), userId, workoutId));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public ResultSet createGetTotalDurationByUserIdQuery(Long userId) {
        try {
            return QueryExecutor.executeQuery(queries.totalDurationByUserId(), userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createGetTotalCaloriesBurnedByUserIdQuery(Long userId) {
        try {
            return QueryExecutor.executeQuery(queries.totalCaloriesBurnedByUserId(), userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createGetCaloriesBurnedByDateQuery(Long userId) {
        try {
            return QueryExecutor.executeQuery(queries.caloriesBurnedByDate(), userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createGetFilteredWorkoutsQuery(Long userId, LocalDate startDate, LocalDate endDate, String typeName) {
        try {
            return QueryExecutor.executeQuery(queries.filteredWorkouts(), userId, startDate, endDate, typeName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
