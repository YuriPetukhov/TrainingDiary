package org.example.core.repository.query_executor;

import org.example.database.DatabaseConnector;
import org.example.database.DatabaseConnectorFactory;

import java.sql.*;

public class QueryExecutor {

    public static Long execute(String query, Object... params) throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnectorFactory.getInstance();
        long id = 0L;
        try (Connection connection = databaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                statement.execute();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            }
        } return id;
    }

    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnectorFactory.getInstance();
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            return statement.executeQuery();
        }
    }

    public static int executeUpdate(String query, Object... params) throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnectorFactory.getInstance();
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            return statement.executeUpdate();
        }
    }

    public static boolean executeDelete(String query, Object... params) throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnectorFactory.getInstance();
        try (Connection connection = databaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                if (params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        statement.setObject(i + 1, params[i]);
                    }
                }
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        }
    }
}
