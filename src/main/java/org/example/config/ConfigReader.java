package org.example.config;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigReader {

    public static Map<String, Object> readConfig(String configFilePath) {
        try (InputStream input = new FileInputStream(configFilePath)) {
            Yaml yaml = new Yaml();
            return yaml.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
