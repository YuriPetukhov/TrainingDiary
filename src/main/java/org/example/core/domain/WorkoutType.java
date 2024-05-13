package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutType extends Type<WorkoutTypeParameter> {

    private Long userId;

    @Override
    protected List<WorkoutTypeParameter> getParametersList() {
        return parameters;
    }
}

