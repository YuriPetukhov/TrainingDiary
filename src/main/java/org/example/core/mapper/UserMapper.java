package org.example.core.mapper;

import org.example.core.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserMapper {
    User toUser(ResultSet resultSet) throws SQLException;
}

