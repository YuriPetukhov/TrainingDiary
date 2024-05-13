package org.example.core.repository.service_response_builder;

import org.example.core.domain.ConcreteWorkoutType;

import java.sql.SQLException;

public interface ConcreteWorkoutTypeRepository extends Repository<ConcreteWorkoutType, Long> {
    ConcreteWorkoutType save(ConcreteWorkoutType concreteWorkoutType);
}
