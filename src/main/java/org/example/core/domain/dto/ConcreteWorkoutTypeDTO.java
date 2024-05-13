package org.example.core.domain.dto;

import lombok.Data;

import java.util.List;
@Data
public class ConcreteWorkoutTypeDTO {
    private String typeName;
    private List<ConcreteParameterDTO> concreteParameters;

    @Override
    public String toString() {
        return String.format("\n%s: %s",
                typeName, concreteParameters);
    }
}
