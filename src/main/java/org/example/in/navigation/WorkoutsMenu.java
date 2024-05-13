package org.example.in.navigation;

import org.example.core.domain.User;
import org.example.in.handler.impl.WorkoutMenuHandler;
import org.example.in.util.MenuCreator;
import org.example.in.util.MenuOptionsBuilder;

import java.util.List;

public class WorkoutsMenu {

    private final MenuCreator menuCreator = new MenuCreator();
    private final MenuOptionsBuilder menuOptionsBuilder = new MenuOptionsBuilder();
    private final WorkoutMenuHandler workoutMenuHandler = new WorkoutMenuHandler();
    private final String MENU_NAME = "workout_menu";

    public void getWorkoutsMenu(User currentUser) {
        while (true) {
            try {
                List<String> menuOptions = menuOptionsBuilder.buildMenuOptions(MENU_NAME);
                int choice = menuCreator.getUserChoose(menuOptions);
                workoutMenuHandler.handle(MENU_NAME, choice, currentUser);

                if (choice == 6) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

}

