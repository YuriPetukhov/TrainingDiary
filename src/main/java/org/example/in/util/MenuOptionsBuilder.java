package org.example.in.util;

import org.example.config.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.config.AppConfig.FILE_MENUS_PATHS_CONFIG;

public class MenuOptionsBuilder {


    public List<String> buildMenuOptions(String menuName) {

        Map<String, Object> pathConfig = ConfigReader.readConfig(FILE_MENUS_PATHS_CONFIG);
        Map<String, Object> path = (Map<String, Object>) pathConfig.get("menu_files");
        String configFilePath = (String) path.get(menuName);

        Map<String, Object> config = ConfigReader.readConfig(configFilePath);
        List<String> menuOptions = new ArrayList<>();

        if (config != null && config.containsKey(menuName)) {
            Map<String, String> menu = (Map<String, String>) config.get(menuName);
            menuOptions.addAll(menu.values());
        }

        return menuOptions;
    }
}
