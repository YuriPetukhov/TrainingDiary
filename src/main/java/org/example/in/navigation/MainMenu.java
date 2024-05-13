package org.example.in.navigation;

import org.example.core.domain.User;
import org.example.in.handler.impl.MainUserChooseHandler;
import org.example.in.util.MenuCreator;
import org.example.in.util.MenuOptionsBuilder;

import java.sql.SQLException;
import java.util.*;

/**
 * Класс для взаимодействия с пользователем в главном меню.
 */
public class MainMenu {

    private final AuthMenu authMenu = new AuthMenu();


    /**
     * Запускает главное меню.
     */
    public void run() throws SQLException {
        User currentUser = null;
        while (currentUser == null) {
            currentUser = authMenu.registerOrauthenticateUser();
        }
        getMainMenu(currentUser);
    }


    /**
     * Выводит главное меню и обрабатывает выбор пользователя.
     *
     * @param currentUser текущий пользователь
     */
    public static void getMainMenu(User currentUser) throws SQLException {
        MenuCreator menuCreator = new MenuCreator();
        MenuOptionsBuilder menuOptionsBuilder = new MenuOptionsBuilder();
        MainUserChooseHandler mainUserChooseHandler = new MainUserChooseHandler();

        String MENU_NAME = "main_menu";

        List<String> menuOptions = menuOptionsBuilder.buildMenuOptions(MENU_NAME);
        int choice = menuCreator.getUserChoose(menuOptions);
        mainUserChooseHandler.handle(MENU_NAME, choice, currentUser);
    }

}
