package org.example.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkoutTypeParameter extends Parameter {

    private Long workoutTypeId;

}
