package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.Log;
import org.example.core.repository.query_builder.LogRepositoryQueryBuilder;
import org.example.core.repository.service_response_builder.LogRepository;

public class LogRepositoryImpl implements LogRepository {

    private final LogRepositoryQueryBuilder queryBuilder = new LogRepositoryQueryBuilder();

    @Override
    public Log save(Log entity) {
        long logId = queryBuilder.createSaveQuery(entity);
        entity.setId(logId);
        return entity;
    }
}
