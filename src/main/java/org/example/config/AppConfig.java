package org.example.config;

import java.util.Map;

public class AppConfig {

    private static final String FILE_LINKS_CONFIG = "src/main/resources/file-links.yml";

    public static Map<String, String> readLinks() {
        Map<String, Object> config = ConfigReader.readConfig(FILE_LINKS_CONFIG);
        return (Map<String, String>) config.get("file_links");
    }

}
