package org.example.core.repository.service_response_builder;

import org.example.core.domain.User;

public interface UserRepository extends Repository<User, Long>, RepositorySave<User, Long>, RepositoryFindByName<User> {
}
