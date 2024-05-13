package org.example.in.util;

import org.example.core.domain.ConcreteWorkoutTypeParameter;
import org.example.core.domain.dto.ConcreteParameterDTO;
import org.example.core.domain.dto.WorkoutDTO;
import org.example.core.service.impl.ConcreteWorkoutParameterServiceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutInputMenuBuilder {

    public Map<String, String> getParametersMap (WorkoutDTO workout) {
        Map<String, String> paramToChange = new HashMap<>();
        int caloriesBurned = workout.getCaloriesBurned();
        int duration = workout.getDuration();
        paramToChange.put("caloriesBurned", Integer.toString(caloriesBurned));
        paramToChange.put("duration", Integer.toString(duration));

       paramToChange.putAll(workout.getParameters());

        return paramToChange;
    }



}
