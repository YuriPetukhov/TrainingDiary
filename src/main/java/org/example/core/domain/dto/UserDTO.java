package org.example.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private List<WorkoutDTO> workouts;

    public UserDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

}
