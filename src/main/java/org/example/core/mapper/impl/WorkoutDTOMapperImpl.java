package org.example.core.mapper.impl;

import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.mapper.WorkoutDTOMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WorkoutDTOMapperImpl implements WorkoutDTOMapper {

    @Override
    public Map<String, List<WorkoutDTO>> map(ResultSet resultSet) throws SQLException {
        Map<String, List<WorkoutDTO>> userWorkouts = new HashMap<>();

        while (resultSet.next()) {
            String user = resultSet.getString("user");
            Date date = resultSet.getDate("date");
            Long workoutId = resultSet.getLong("workout_id");
            int duration = resultSet.getInt("duration");
            int caloriesBurned = resultSet.getInt("calories_burned");
            String concreteWorkoutTypeName = resultSet.getString("concrete_workout_type");

            // Получаем массив параметров
            Array parametersArray = resultSet.getArray("parameters");
            Map<String, String> workoutParameters = new HashMap<>();

            if (parametersArray != null) {
                // Разбираем массив параметров
                Object[] parameters = (Object[]) parametersArray.getArray();
                for (Object parameter : parameters) {
                    String[] keyValue = ((String) parameter).split(":");
                    workoutParameters.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            WorkoutDTO workout = new WorkoutDTO(workoutId, user, date, duration, caloriesBurned, concreteWorkoutTypeName, workoutParameters);

            if (!userWorkouts.containsKey(user)) {
                userWorkouts.put(user, new ArrayList<>());
            }
            userWorkouts.get(user).add(workout);
        }

        return userWorkouts;
    }

    @Override
    public Optional<WorkoutDTO> toWorkoutDTO(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                return Optional.empty();
            }

            String user = resultSet.getString("user");
            Date date = resultSet.getDate("date");
            long workoutId = resultSet.getLong("workout_id");
            int duration = resultSet.getInt("duration");
            int caloriesBurned = resultSet.getInt("calories_burned");
            String concreteWorkoutTypeName = resultSet.getString("concrete_workout_type");

            Array parametersArray = resultSet.getArray("parameters");
            Map<String, String> workoutParameters = new HashMap<>();

            if (parametersArray != null) {
                Object[] parameters = (Object[]) parametersArray.getArray();
                for (Object parameter : parameters) {
                    String[] keyValue = ((String) parameter).split(":");
                    workoutParameters.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            WorkoutDTO workout = new WorkoutDTO(workoutId, user, date, duration, caloriesBurned, concreteWorkoutTypeName, workoutParameters);

            return Optional.of(workout);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}