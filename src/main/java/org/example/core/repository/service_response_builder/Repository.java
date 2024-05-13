package org.example.core.repository.service_response_builder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    Optional<T> update(T entity) throws SQLException;

    void delete(ID id) throws SQLException;

    Optional<T> findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

}
