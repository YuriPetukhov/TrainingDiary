package org.example.in.handler.impl;

import org.example.core.domain.User;
import org.example.in.handler.Handle;
import org.example.in.navigation.StatisticsMenu;
import org.example.in.navigation.WorkoutsMenu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainUserChooseHandler implements Handle {

    public MainUserChooseHandler() {
        init();
    }

    @FunctionalInterface
    interface Command {
        void run(User currentUser) throws SQLException;
    }

    private final Map<Integer, Command> mainMenuCommandMap = new HashMap<>();
    private final StatisticsMenu statisticsMenu = new StatisticsMenu();
    private final WorkoutsMenu workoutsMenu = new WorkoutsMenu();

    public void init() {
        mainMenuCommandMap.put(1, workoutsMenu::getWorkoutsMenu);
        mainMenuCommandMap.put(2, statisticsMenu::getStatisticsMenu);
        mainMenuCommandMap.put(3, currentUser -> {
            System.out.println("До свидания");
            System.exit(0);
        });
    }


    @Override
    public void handle(String menu, Integer choose, User currentUser) throws SQLException {
        Command commandToRun = mainMenuCommandMap.get(choose);
        if (commandToRun != null) {
            commandToRun.run(currentUser);
        } else {
            System.out.println("Неверный выбор");
        }
    }
}
