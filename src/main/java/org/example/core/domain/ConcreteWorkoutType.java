package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteWorkoutType extends Type<ConcreteWorkoutTypeParameter> {

    private Long workoutId;

    private List<ConcreteWorkoutTypeParameter> concreteParameters;

    public void addConcreteParameter(ConcreteWorkoutTypeParameter parameter) {
        concreteParameters.add(parameter);
    }

    @Override
    protected List<ConcreteWorkoutTypeParameter> getParametersList() {
        return concreteParameters;
    }
}
