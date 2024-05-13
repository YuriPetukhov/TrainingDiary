package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteWorkoutTypeParameter extends Parameter {

    private Long concreteWorkoutTypeId;
    private Integer value;
}
