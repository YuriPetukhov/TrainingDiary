package org.example.in.handler.impl;

import org.example.core.domain.User;
import org.example.in.comminication.StatisticsUserInteraction;
import org.example.in.handler.Handle;
import org.example.in.navigation.MainMenu;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatisticMenuHandler implements Handle {

    public StatisticMenuHandler() {

        init();
    }

    @FunctionalInterface
    interface Command {
        void run(User currentUser) throws SQLException;
    }
    private final Map<Integer, Command> statisticMenuCommandMap = new HashMap<>();
    private final StatisticsUserInteraction statisticsUserInteraction = new StatisticsUserInteraction();

    public void init() {
        statisticMenuCommandMap.put(1, statisticsUserInteraction::getCaloriesBurnedTotal);
        statisticMenuCommandMap.put(2, statisticsUserInteraction::getCaloriesBurnedByDate);
        statisticMenuCommandMap.put(3, statisticsUserInteraction::getDurationTotal);
        statisticMenuCommandMap.put(4, statisticsUserInteraction::getFilteredWorkouts);
        statisticMenuCommandMap.put(5, MainMenu::getMainMenu);
    }

    @Override
    public void handle(String menu, Integer choose, User currentUser) throws SQLException {
        Command commandToRun = statisticMenuCommandMap.get(choose);
        if (commandToRun != null) {
            commandToRun.run(currentUser);
        } else {
            System.out.println("Неверный выбор");
        }
    }
}
