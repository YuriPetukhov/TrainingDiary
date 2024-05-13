package org.example.main;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.config.ConfigReader;
import org.example.database.DatabaseConnectorFactory;
import org.example.in.navigation.MainMenu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.example.config.AppConfig.LIQUIBASE_CONFIG_FILE;

/**
 * Точка входа в приложение.
 */
public class TrainingDiaryApp {
    public static void main(String[] args) throws SQLException {

        Map<String, Object> config = ConfigReader.readConfig(LIQUIBASE_CONFIG_FILE);
        Map<String, Object> liquibaseConfig = (Map<String, Object>) config.get("liquibase");
        String changeLogFile = (String) liquibaseConfig.get("change-log");
        String defaultSchemaName = (String) liquibaseConfig.get("default-schema-name");
        String liquibaseSchemaName = (String) liquibaseConfig.get("liquibase-schema-name");
        runLiquibase(changeLogFile, defaultSchemaName, liquibaseSchemaName);
        MainMenu mainMenu = new MainMenu();

        mainMenu.run();
    }

    private static void runLiquibase(String changeLogFile, String defaultSchemaName, String liquibaseSchemaName) {
        try (Connection connection = DatabaseConnectorFactory.getInstance().getConnection()) {
            DatabaseConnection databaseConnection = new JdbcConnection(connection);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(databaseConnection);
            database.setDefaultSchemaName(defaultSchemaName);
            database.setLiquibaseSchemaName(liquibaseSchemaName);
            Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
