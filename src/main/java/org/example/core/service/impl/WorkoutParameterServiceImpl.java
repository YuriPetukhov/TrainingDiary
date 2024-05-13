package org.example.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.WorkoutType;
import org.example.core.domain.WorkoutTypeParameter;
import org.example.core.repository.service_response_builder.impl.WorkoutParameterRepositoryImpl;
import org.example.core.service.WorkoutParameterService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class WorkoutParameterServiceImpl implements WorkoutParameterService {

    private final WorkoutParameterRepositoryImpl workoutParameterRepositoryImpl = new WorkoutParameterRepositoryImpl();
    public WorkoutTypeParameter addWorkoutParameter(String parameterName, Long workoutTypeId) {
        WorkoutTypeParameter workoutTypeParameter = new WorkoutTypeParameter();
        workoutTypeParameter.setParameterName(parameterName);
        workoutTypeParameter.setWorkoutTypeId(workoutTypeId);
        workoutParameterRepositoryImpl.save(workoutTypeParameter);
        return workoutTypeParameter;
    }

    public WorkoutTypeParameter createWorkoutParameter(WorkoutTypeParameter workoutTypeParameter) {
        workoutParameterRepositoryImpl.save(workoutTypeParameter);
        return workoutTypeParameter;
    }


    public void updateWorkoutParameter(WorkoutTypeParameter parameter) {

    }

    public void deleteWorkoutParameter(Long id) {
        WorkoutTypeParameter parameter = getWorkoutParameterById(id);
        if (parameter != null) {
            workoutParameterRepositoryImpl.delete(id);
        } else {
            System.out.println("Параметр тренировки с указанным идентификатором не найден.");
        }
    }

    public WorkoutTypeParameter getWorkoutParameterById(Long id) {
        Optional<WorkoutTypeParameter> optionalWorkoutParameter = null;
        try {
            optionalWorkoutParameter = workoutParameterRepositoryImpl.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalWorkoutParameter.orElse(null);
    }

    public void addWorkoutParameters(Set<String> typeParameters, WorkoutType workoutType) {
        for(String typeParameter : typeParameters) {
            addWorkoutParameter(typeParameter, workoutType.getId());
        }
    }

    public List<String> findWorkoutParameters(Long id) throws SQLException {
        return workoutParameterRepositoryImpl.findAllByWorkoutTypeId(id);
    }
}
