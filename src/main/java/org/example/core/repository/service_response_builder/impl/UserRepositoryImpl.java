package org.example.core.repository.service_response_builder.impl;

import org.example.core.domain.User;
import org.example.core.repository.service_response_builder.UserRepository;
import org.example.core.mapper.impl.UserMapperImpl;
import org.example.core.repository.query_builder.UserRepositoryQueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

private final UserMapperImpl userMapper = new UserMapperImpl();
private final UserRepositoryQueryBuilder queryBuilder = new UserRepositoryQueryBuilder();

    @Override
    public User save(User user) {
        long userId = queryBuilder.createSaveQuery(user);
        user.setId(userId);
        return user;
    }

    @Override
    public Optional<User> update(User user) {
        queryBuilder.createUpdateQuery(user);
        try {
            return findById(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        ResultSet resultSet = queryBuilder.createFindAllQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = userMapper.toUser(resultSet);
            users.add(user);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByIdQuery(id);
        if (resultSet.next()) {
            return Optional.of(userMapper.toUser(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        queryBuilder.createDeleteQuery(id);
    }

    @Override
    public Optional<User> findByName(String username) throws SQLException {
        ResultSet resultSet = queryBuilder.createFindByNameQuery(username);
        if (resultSet.next()) {
            return Optional.of(userMapper.toUser(resultSet));
        } else {
            return Optional.empty();
        }
    }
}

