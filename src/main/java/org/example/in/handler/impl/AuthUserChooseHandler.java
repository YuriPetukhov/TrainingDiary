package org.example.in.handler.impl;

import org.example.core.domain.User;
import org.example.in.comminication.AuthUserInteraction;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthUserChooseHandler {

    private final User currentUser;

    @FunctionalInterface
    interface Command {
        User run(Integer choose);
    }

    Map<Integer, Command> authMenuCommandMap = new HashMap<>();
    AuthUserInteraction authUserInteraction = new AuthUserInteraction();

    public AuthUserChooseHandler() {
        currentUser = init();
    }

    public User init() {
        authMenuCommandMap.put(1, choose -> {
            try {
                return authUserInteraction.registerUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        authMenuCommandMap.put(2, choose -> authUserInteraction.authenticateUser());
        return currentUser;
    }

    public User handle(Integer choose) {
        Command commandToRun = authMenuCommandMap.get(choose);
        if (commandToRun != null) {
            return commandToRun.run(choose);
        } else {
            System.exit(0);
            return null;
        }
    }
}

