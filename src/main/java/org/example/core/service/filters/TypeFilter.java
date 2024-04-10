package org.example.core.service.filters;

import org.example.core.domain.Workout;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для тренировок по типу.
 */
public class TypeFilter implements WorkoutPredicate {
    private final String type;

    public TypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean test(Workout workout) {
        return workout.getType().equalsIgnoreCase(type);
    }
}

