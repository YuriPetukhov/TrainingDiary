package org.example.in.handler.impl;

import org.example.core.domain.User;
import org.example.in.comminication.WorkoutUserInteraction;
import org.example.in.handler.Handle;
import org.example.in.navigation.MainMenu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WorkoutMenuHandler implements Handle {
    public WorkoutMenuHandler() {

        init();
    }

    @FunctionalInterface
    interface Command {
        void run(User currentUser) throws SQLException;
    }
    private final Map<Integer, Command> workoutMenuCommandMap = new HashMap<>();
    private final WorkoutUserInteraction workoutUserInteraction = new WorkoutUserInteraction();

    public void init() {
        workoutMenuCommandMap.put(1, workoutUserInteraction::addNewWorkoutType);
        workoutMenuCommandMap.put(2, workoutUserInteraction::addWorkout);
        workoutMenuCommandMap.put(3, workoutUserInteraction::viewWorkouts);
        workoutMenuCommandMap.put(4, workoutUserInteraction::editWorkout);
        workoutMenuCommandMap.put(5, workoutUserInteraction::deleteWorkout);
        workoutMenuCommandMap.put(6, MainMenu::getMainMenu);
    }

    @Override
    public void handle(String menu, Integer choose, User currentUser) throws SQLException {
        Command commandToRun = workoutMenuCommandMap.get(choose);
        if (commandToRun != null) {
            commandToRun.run(currentUser);
        } else {
            System.out.println("Неверный выбор");
        }
    }
}
