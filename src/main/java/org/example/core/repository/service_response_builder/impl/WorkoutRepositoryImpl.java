package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.repository.service_response_builder.RepositorySave;
import org.example.core.repository.service_response_builder.WorkoutRepository;
import org.example.core.mapper.impl.WorkoutDTOMapperImpl;
import org.example.core.repository.query_builder.WorkoutRepositoryQueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class WorkoutRepositoryImpl implements WorkoutRepository,
        RepositorySave<Workout, Long> {

    private static final WorkoutDTOMapperImpl workoutMapper = new WorkoutDTOMapperImpl();
    private final WorkoutRepositoryQueryBuilder queryBuilder = new WorkoutRepositoryQueryBuilder();
    private final ConcreteWorkoutTypeRepositoryImpl concreteWorkoutTypeRepository = new ConcreteWorkoutTypeRepositoryImpl();


    public Workout save(Workout workout) {
        if (workout.getId() != null) {
            try {
                return updateTypId(workout);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return insert(workout);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Workout insert(Workout workout) throws SQLException {
        long workoutId = queryBuilder.createInsertQuery(workout);
        workout.setId(workoutId);
        return workout;
    }

    private Workout updateTypId(Workout workout) throws SQLException {
        queryBuilder.createUpdateQuery(workout);
        return workout;
    }

    @Override
    public Optional<Workout> update(Workout entity) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) throws SQLException {
    }

    public boolean deleteWorkout(Long workoutId, Long userId, Long concreteWorkoutTypeId) {
        concreteWorkoutTypeRepository.delete(concreteWorkoutTypeId);
        return queryBuilder.createDeleteQuery(workoutId, userId);

    }

    @Override
    public Optional<Workout> findById(Long id) throws SQLException {
        try (ResultSet resultSet = queryBuilder.createFindByIdQuery(id)) {
            if (resultSet.next()) {
                LocalDate date = resultSet.getDate("date").toLocalDate();
                Long userId = resultSet.getLong("user_id");
                int duration = resultSet.getInt("duration");
                int caloriesBurned = resultSet.getInt("calories_burned");
                long concreteWorkoutTypeId = resultSet.getLong("concrete_workout_type_id");

                Workout workout = new Workout();
                workout.setId(id);
                workout.setDate(date);
                workout.setDuration(duration);
                workout.setCaloriesBurned(caloriesBurned);
                workout.setUserId(userId);
                if (concreteWorkoutTypeId != 0) {
                    workout.setConcreteWorkoutTypeId(concreteWorkoutTypeId);
                }

                return Optional.of(workout);
            }
        }

        return Optional.empty();
    }

    public List<Workout> findAll() throws SQLException {
        ResultSet resultSet = queryBuilder.createFindAllQuery();
        return null;
    }

    public Map<String, List<WorkoutDTO>> findAllDTOs() {
        ResultSet resultSet = queryBuilder.createFindAllDTOsQuery();
        try {
            return workoutMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<WorkoutDTO>> findAllDTOsByUser(User currentUser) {
        ResultSet resultSet = queryBuilder.createFindAllDTOsByUserQuery(currentUser.getId());
        try {
            return workoutMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isWorkTypeWithNameAndUserIdAndDateExists(Long id, LocalDate date, String workoutType) {
        try (ResultSet resultSet = queryBuilder.createIsWorkTypeWithNameAndUserIdAndDateExistsQuery(id, date, workoutType)) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<WorkoutDTO> getWorkoutByIdAndUserId(Long workoutId, Long userId) {
        Optional<ResultSet> optResultSet = queryBuilder.createFindUserWorkoutQuery(workoutId, userId);
        if (optResultSet.isPresent()) {
            return workoutMapper.toWorkoutDTO(optResultSet.get());
        } else {
            return Optional.empty();
        }
    }

    public void createUpdateConcreteWorkoutTypeIdQuery(Workout workout) {
        queryBuilder.createUpdateConcreteWorkoutTypeIdQuery(workout.getId(), workout.getConcreteWorkoutTypeId());
    }

    public int getTotalDurationByUserId(Long userId) throws SQLException {
        int totalDuration = 0;
        try (ResultSet resultSet = queryBuilder.createGetTotalDurationByUserIdQuery(userId)) {
            if (resultSet.next()) {
                totalDuration = resultSet.getInt(1);
            }
        }
        return totalDuration;

    }

    public int getTotalCaloriesBurnedByUserId(Long userId) throws SQLException {
        int totalCaloriesBurned = 0;
        try (ResultSet resultSet = queryBuilder.createGetTotalCaloriesBurnedByUserIdQuery(userId)) {
            if (resultSet.next()) {
                totalCaloriesBurned = resultSet.getInt(1);
            }
        }
        return totalCaloriesBurned;
    }

    public Map<String, Integer> getCaloriesBurnedByDate(Long userId) throws SQLException {
        Map<String, Integer> caloriesBurnedByDate = new HashMap<>();
        try (ResultSet resultSet = queryBuilder.createGetCaloriesBurnedByDateQuery(userId)) {
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                int caloriesBurned = resultSet.getInt("total_calories_burned");
                caloriesBurnedByDate.merge(date, caloriesBurned, Integer::sum);
            }
        }
        return caloriesBurnedByDate;
    }

    public Map<String, List<WorkoutDTO>> getFilteredWorkouts(Long userId, LocalDate startDate, LocalDate endDate, String typeName) {
        ResultSet resultSet = queryBuilder.createGetFilteredWorkoutsQuery(userId, startDate, endDate, typeName);
        try {
            return workoutMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
