package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.core.enums.UserRole;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity {

    private String username;
    private String password;
    private UserRole role;
    private List<Workout> workouts;
    private List<WorkoutType> workoutTypes;

    public User(String username, Long userId) {
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        workout.setUserId(this.getId());
    }

}

