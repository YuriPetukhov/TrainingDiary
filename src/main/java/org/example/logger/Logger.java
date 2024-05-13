package org.example.logger;

import org.example.core.domain.Log;
import org.example.core.repository.service_response_builder.LogRepository;
import org.example.core.repository.service_response_builder.impl.LogRepositoryImpl;

import java.time.LocalDateTime;

/**
 * Сервис для ведения логов.
 */
public class Logger {
    private final LogRepository logRepository = new LogRepositoryImpl();

    public void log(String action, String user, boolean success) {
        Log log = new Log(action, user, LocalDateTime.now(), success);
        logRepository.save(log);
    }
}