package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.enums.UserRole;

import java.util.List;

public interface UserService {
    User addUser(String username, String password, UserRole role);

    User updateUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    void addWorkout(Workout newWorkout, Long id);

    User getUserById(Long id);
}
