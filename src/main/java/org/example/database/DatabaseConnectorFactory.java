package org.example.database;

import org.example.config.ConfigReader;

import java.util.Map;

public class DatabaseConnectorFactory {
    private static DatabaseConnector instance;
    private static final String CONFIG_FILE = "src/main/resources/config.yml";

    private DatabaseConnectorFactory() {
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            Map<String, Object> config = ConfigReader.readConfig(CONFIG_FILE);
            Map<String, Object> datasourceConfig = (Map<String, Object>) config.get("datasource");
            String url = (String) datasourceConfig.get("url");
            String user = (String) datasourceConfig.get("username");
            String password = (String) datasourceConfig.get("password");
            instance = new DatabaseConnector(url, user, password);
        }
        return instance;
    }
}