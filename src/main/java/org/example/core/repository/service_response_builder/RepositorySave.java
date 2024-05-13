package org.example.core.repository.service_response_builder;

public interface RepositorySave<T, ID> {
    T save(T entity);
}
