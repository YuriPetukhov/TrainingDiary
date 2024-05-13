package org.example.in.navigation;

import org.example.core.domain.User;
import org.example.in.handler.impl.StatisticMenuHandler;
import org.example.in.util.MenuCreator;
import org.example.in.util.MenuOptionsBuilder;

import java.sql.SQLException;
import java.util.List;

public class StatisticsMenu {
    private final MenuCreator menuCreator = new MenuCreator();
    private final MenuOptionsBuilder menuOptionsBuilder = new MenuOptionsBuilder();
    private final StatisticMenuHandler statisticMenuHandler = new StatisticMenuHandler();
    private final String MENU_NAME = "statistic_menu";

    /**
     * Выводит меню статистики и обрабатывает выбор пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public void getStatisticsMenu(User currentUser) throws SQLException {

        boolean keepRunning = true;

        while (keepRunning) {
            List<String> menuOptions = menuOptionsBuilder.buildMenuOptions(MENU_NAME);
            int choice = menuCreator.getUserChoose(menuOptions);
            statisticMenuHandler.handle(MENU_NAME, choice, currentUser);

            if (choice == 5) {
                keepRunning = false;
            }
        }

    }
}
