package org.example.in.util;

import org.example.in.validation.InputValidator;

import java.util.HashMap;
import java.util.Map;

public class WorkoutInputMenuCreator {

    private final InputValidator inputValidator = new InputValidator();

    public Map<String, Integer> getNewParameters(Map<String, String> parameters) {
        Map<String, Integer> changedParameters = new HashMap<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String parameterName = entry.getKey();
            String currentValue = entry.getValue();

            System.out.format("Текущее значение параметра %s: %s\n", parameterName, currentValue);
            System.out.println("Введите новое значение или для сохранения текущего значения нажмите Enter:");
            Integer newValue = inputValidator.validateNeuValue();

            if (newValue != 0) {
                changedParameters.put(parameterName, newValue);
            } else {
                changedParameters.put(parameterName, Integer.valueOf(currentValue));
            }
        }
        return changedParameters;
    }
}

