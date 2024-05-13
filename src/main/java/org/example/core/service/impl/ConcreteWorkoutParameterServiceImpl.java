package org.example.core.service.impl;

import org.example.core.domain.ConcreteWorkoutTypeParameter;
import org.example.core.domain.dto.ConcreteParameterDTO;
import org.example.core.repository.service_response_builder.impl.ConcreteWorkoutParameterRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ConcreteWorkoutParameterServiceImpl {

    private final ConcreteWorkoutParameterRepositoryImpl concreteWorkoutParameterRepositoryImpl = new ConcreteWorkoutParameterRepositoryImpl();
    public void addConcreteWorkoutTypeParameters(Long id, Map<String, Integer> concreteParameters) throws SQLException {
        concreteWorkoutParameterRepositoryImpl.saveAll(id, concreteParameters);
    }

    public void updateConcreteParameterValues(Long typeId, Map<String, Integer> newParameters) throws SQLException {
        List<ConcreteWorkoutTypeParameter> concreteParameters = concreteWorkoutParameterRepositoryImpl.findByTypeId(typeId);
        for (Map.Entry<String, Integer> entry : newParameters.entrySet()) {
            String parameterName = entry.getKey();
            Integer newValue = entry.getValue();

            for(ConcreteWorkoutTypeParameter parameter : concreteParameters) {
                if ( parameter.getParameterName().equals(parameterName)){
                    parameter.setValue(newValue);
                    concreteWorkoutParameterRepositoryImpl.update(parameter);
                }
            }
        }
    }

    public void deleteParameters(Long concreteWorkoutTypeId) {
        concreteWorkoutParameterRepositoryImpl.delete(concreteWorkoutTypeId);
    }
}
