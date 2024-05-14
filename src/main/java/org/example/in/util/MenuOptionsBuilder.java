package org.example.in.util;

import org.example.config.AppConfig;
import org.example.config.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuOptionsBuilder {


    public List<String> buildMenuOptions(String menuName) {
        Map<String, String> links = AppConfig.readLinks();
        Map<String, Object> pathConfig = ConfigReader.readConfig(links.get("FILE_MENUS_PATHS_CONFIG"));
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
