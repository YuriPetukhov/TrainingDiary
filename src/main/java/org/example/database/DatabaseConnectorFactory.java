package org.example.database;

import org.example.config.ConfigReader;

import java.util.Map;

public class DatabaseConnectorFactory {
    private static DatabaseConnector instance;


    private DatabaseConnectorFactory() {
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            Map<String, Object> config = ConfigReader.readConfig("src/main/resources/config.yml");
            Map<String, Object> datasourceConfig = (Map<String, Object>) config.get("datasource");
            String url = (String) datasourceConfig.get("url");
            String user = (String) datasourceConfig.get("username");
            String password = (String) datasourceConfig.get("password");
            instance = new DatabaseConnector(url, user, password);
        }
        return instance;
    }
}