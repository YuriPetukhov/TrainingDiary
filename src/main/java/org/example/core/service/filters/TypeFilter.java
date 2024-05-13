package org.example.core.service.filters;

import org.example.core.domain.Workout;


/**
 * Фильтр для тренировок по типу.
 */
public class TypeFilter implements WorkoutPredicate {
    private final Long typeId;

    public TypeFilter(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean test(Workout workout) {
        return false; //workout.getConcreteWorkoutType().getId().equals(typeId);
    }
}

