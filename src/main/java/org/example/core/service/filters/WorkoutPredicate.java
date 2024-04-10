package org.example.core.service.filters;

import org.example.core.domain.Workout;

import java.util.List;

public interface WorkoutPredicate {
    boolean test(Workout workout);
}
