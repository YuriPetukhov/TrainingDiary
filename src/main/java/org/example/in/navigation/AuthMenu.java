package org.example.in.navigation;

import org.example.core.domain.User;
import org.example.in.handler.impl.AuthUserChooseHandler;
import org.example.in.util.MenuCreator;
import org.example.in.util.MenuOptionsBuilder;

import java.util.List;

public class AuthMenu {

    private final AuthUserChooseHandler authUserChooseHandler = new AuthUserChooseHandler();
    private final MenuCreator menuCreator = new MenuCreator();
    private final MenuOptionsBuilder menuOptionsBuilder = new MenuOptionsBuilder();
    private final String MENU_NAME = "auth_menu";

    /**
     * Предъявляет пользователю меню и позволяет ему зарегистрироваться или пройти аутентификацию.
     *
     * @return аутентифицированный пользователь или `null`, если пользователь решил выйти из программы.
     */
    public User registerOrauthenticateUser() {

        List<String> menuOptions = menuOptionsBuilder.buildMenuOptions(MENU_NAME);

        int choice = menuCreator.getUserChoose(menuOptions);

        return authUserChooseHandler.handle(choice);
    }

}
