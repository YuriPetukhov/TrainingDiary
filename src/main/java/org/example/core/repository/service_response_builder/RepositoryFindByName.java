package org.example.core.repository.service_response_builder;

import java.sql.SQLException;
import java.util.Optional;

public interface RepositoryFindByName<T> {
    Optional<T> findByName(String name) throws SQLException;
}
