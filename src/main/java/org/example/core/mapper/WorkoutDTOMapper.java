package org.example.core.mapper;

import org.example.core.domain.dto.UserDTO;
import org.example.core.domain.dto.WorkoutDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkoutDTOMapper {
    Map<String, List<WorkoutDTO>> map(ResultSet resultSet) throws SQLException;

    Optional<WorkoutDTO> toWorkoutDTO(ResultSet resultSet);
}
