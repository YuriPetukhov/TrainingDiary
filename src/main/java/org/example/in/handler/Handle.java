package org.example.in.handler;

import org.example.core.domain.User;

import java.sql.SQLException;

public interface Handle {
    void handle(String menu, Integer choose, User currentUser) throws SQLException;
}
