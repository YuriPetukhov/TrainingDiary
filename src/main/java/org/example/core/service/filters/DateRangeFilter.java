package org.example.core.service.filters;

import org.example.core.domain.Workout;

import java.time.LocalDate;

/**
 * Фильтр для тренировок по диапазону дат.
 */
public class DateRangeFilter implements WorkoutPredicate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateRangeFilter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(Workout workout) {
        return !workout.getDate().isBefore(startDate) && !workout.getDate().isAfter(endDate);
    }
}
