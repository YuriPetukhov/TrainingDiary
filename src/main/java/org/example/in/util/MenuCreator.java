package org.example.in.util;

import org.example.in.validation.InputValidator;

import java.util.List;

public class MenuCreator {

    private final InputValidator inputValidator = new InputValidator();

    public int getUserChoose(List<String> menuOptions){
        System.out.println("Выберите пункт:");
        for (int i = 0; i < menuOptions.size(); i++) {
            System.out.println(i + 1 + ". " + menuOptions.get(i));
        }
        return inputValidator.promptForNumberChoose(1, menuOptions.size());
    }
}
