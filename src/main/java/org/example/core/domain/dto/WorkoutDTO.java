package org.example.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.domain.ConcreteWorkoutTypeParameter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class WorkoutDTO {
    private Long workoutId;
    private String userName;
    private Date date;
    private int duration;
    private int caloriesBurned;
    private String concreteWorkoutTypeName;
    private Map<String, String> parameters;


    public WorkoutDTO(Long workoutId, String userName, Date date, int duration, int caloriesBurned, String concreteWorkoutTypeName, Map<String, String> parameters) {
        this.workoutId = workoutId;
        this.userName = userName;
        this.date = date;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.concreteWorkoutTypeName = concreteWorkoutTypeName;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Номер %d. тип: %s (дата: %s) - продолжительность: %d минут, расход калорий: %d калорий",
                workoutId, concreteWorkoutTypeName, date, duration, caloriesBurned));
        if (!parameters.isEmpty()) {
            sb.append("\nДополнительные параметры:");
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                sb.append(String.format("\n- %s: %s", entry.getKey(), entry.getValue()));
            }
        }
        sb.append("\n");
        return sb.toString();
    }

}
