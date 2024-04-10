package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @AfterEach
    void tearDown() {
        userService.getAllUsers().clear();
    }

    @Test
    @DisplayName("Проверка добавления пользователя")
    void addUser() {
        User user = userService.addUser("username", "password", false);
        assertNotNull(user);
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertFalse(user.isAdmin());
    }

    @Test
    @DisplayName("Проверка получения всех пользователей")
    void getAllUsers() {
        User user1 = userService.addUser("username1", "password1", false);
        User user2 = userService.addUser("username2", "password2", true);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    @DisplayName("Проверка добавления тренировки пользователю")
    void addWorkout() {
        User user = userService.addUser("username", "password", false);
        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 300);

        userService.addWorkout(workout, user);

        assertEquals(1, user.getWorkouts().size());
        assertTrue(user.getWorkouts().contains(workout));
    }

    @Test
    @DisplayName("Проверка добавления тренировки пользователю, если тренировка уже существует")
    void addWorkout_WorkoutAlreadyExists() {
        User user = userService.addUser("username", "password", false);
        Workout workout1 = new Workout(LocalDate.now(), "Бег", 10, 300);
        Workout workout2 = new Workout(LocalDate.now(), "Бег", 10, 300);

        userService.addWorkout(workout1, user);
        userService.addWorkout(workout2, user);

        assertEquals(1, user.getWorkouts().size());
        assertTrue(user.getWorkouts().contains(workout1));
        assertFalse(user.getWorkouts().contains(workout2));
    }

    @Test
    @DisplayName("Проверка получения пользователя по имени")
    void getUserByUsername() {
        User user1 = userService.addUser("username1", "password1", false);
        User user2 = userService.addUser("username2", "password2", true);

        User foundUser1 = userService.getUserByUsername("username1");
        User foundUser2 = userService.getUserByUsername("username2");

        assertEquals(user1, foundUser1);
        assertEquals(user2, foundUser2);
    }

    @Test
    @DisplayName("Проверка получения пользователя по имени, если пользователь не найден")
    void getUserByUsername_UserNotFound() {
        List<User> initialUsers = new ArrayList<>(userService.getAllUsers());
        User user = userService.getUserByUsername("non-existing-username");
        assertEquals(initialUsers, userService.getAllUsers());
        assertNull(user);
    }

}
