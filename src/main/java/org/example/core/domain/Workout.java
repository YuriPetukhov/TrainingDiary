package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workout extends Entity {

    private LocalDate date;
    private Long concreteWorkoutTypeId;
    private int duration;
    private int caloriesBurned;
    private Long userId;

}
