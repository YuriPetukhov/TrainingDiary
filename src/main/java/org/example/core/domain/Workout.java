package org.example.core.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс Workout представляет собой модель тренировки.
 */
public class Workout {

    /**
     * Уникальный идентификатор тренировки.
     */
    private final int id;

    /**
     * Дата тренировки.
     */
    private LocalDate date;

    /**
     * Тип тренировки.
     */
    private String type;

    /**
     * Продолжительность тренировки в минутах.
     */
    private int duration;

    /**
     * Количество сожженных калорий.
     */
    private int caloriesBurned;

    /**
     * Дополнительные параметры тренировки.
     */
    private Map<String, Object> additionalParameters;

    /**
     * Счетчик для генерации уникальных идентификаторов.
     */
    private static int nextId = 1;

    /**
     * Конструктор класса Workout.
     *
     * @param date дата тренировки
     * @param type тип тренировки
     * @param duration продолжительность тренировки в минутах
     * @param caloriesBurned количество сожженных калорий
     */
    public Workout(LocalDate date, String type, int duration, int caloriesBurned) {
        this.id = nextId++;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.additionalParameters = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d. %s (%s) - %d минут, %d калорий", id, date, type, duration, caloriesBurned));
        if (!additionalParameters.isEmpty()) {
            sb.append("\nДополнительные параметры:");
            for (Map.Entry<String, Object> entry : additionalParameters.entrySet()) {
                sb.append(String.format("\n%s: %s", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString();
    }
}
