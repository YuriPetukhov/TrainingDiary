//package org.example.core.service;
//
//import org.example.core.domain.User;
//import org.example.core.domain.Workout;
//import org.example.core.enums.UserRole;
//import org.example.core.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserServiceImplTest {
//
//    private UserServiceImpl userServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        userServiceImpl = new UserServiceImpl();
//    }
//
//    @AfterEach
//    void tearDown() throws NoSuchFieldException, IllegalAccessException {
//        Field usersField = userServiceImpl.getClass().getDeclaredField("users");
//        usersField.setAccessible(true);
//        ((HashMap<?, ?>) usersField.get(userServiceImpl)).clear();
//    }
//
//
//    @Test
//    @DisplayName("Проверка добавления пользователя")
//    void addUser() {
//        User user = userServiceImpl.addUser("username", "password", UserRole.USER);
//        assertNotNull(user);
//        assertEquals("username", user.getUsername());
//        assertEquals("password", user.getPassword());
//        assertNotEquals(user.getRole(), UserRole.ADMIN);
//    }
//
//    @Test
//    @DisplayName("Проверка получения всех пользователей")
//    void getAllUsers() {
//        User user1 = userServiceImpl.addUser("username1", "password1", UserRole.USER);
//        User user2 = userServiceImpl.addUser("username2", "password2", UserRole.ADMIN);
//
//        List<User> users = userServiceImpl.getAllUsers();
//        assertEquals(2, users.size());
//        assertTrue(users.contains(user1));
//        assertTrue(users.contains(user2));
//    }
//
//    @Test
//    @DisplayName("Проверка добавления тренировки пользователю")
//    void addWorkout() {
//        User user = userServiceImpl.addUser("username", "password", UserRole.USER);
//        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 300);
//
//        userServiceImpl.addWorkout(workout, user);
//
//        assertEquals(1, user.getWorkouts().size());
//        assertTrue(user.getWorkouts().containsValue(workout));
//    }
//
//    @Test
//    @DisplayName("Проверка добавления тренировки пользователю, если тренировка уже существует")
//    void addWorkout_WorkoutAlreadyExists() {
//        User user = userServiceImpl.addUser("username", "password", UserRole.USER);
//        Workout workout1 = new Workout(LocalDate.now(), "Бег", 10, 300);
//        Workout workout2 = new Workout(LocalDate.now(), "Бег", 10, 300);
//
//        userServiceImpl.addWorkout(workout1, user);
//        userServiceImpl.addWorkout(workout2, user);
//
//        assertEquals(1, user.getWorkouts().size());
//        assertTrue(user.getWorkouts().containsValue(workout1));
//        assertFalse(user.getWorkouts().containsValue(workout2));
//    }
//
//    @Test
//    @DisplayName("Проверка получения пользователя по имени")
//    void getUserByUsername() {
//        User user1 = userServiceImpl.addUser("username1", "password1", UserRole.USER);
//        User user2 = userServiceImpl.addUser("username2", "password2", UserRole.ADMIN);
//
//        User foundUser1 = userServiceImpl.getUserByUsername("username1");
//        User foundUser2 = userServiceImpl.getUserByUsername("username2");
//
//        assertEquals(user1, foundUser1);
//        assertEquals(user2, foundUser2);
//    }
//
//    @Test
//    @DisplayName("Проверка получения пользователя по имени, если пользователь не найден")
//    void getUserByUsername_UserNotFound() {
//        List<User> initialUsers = new ArrayList<>(userServiceImpl.getAllUsers());
//        User user = userServiceImpl.getUserByUsername("non-existing-username");
//        assertEquals(initialUsers, userServiceImpl.getAllUsers());
//        assertNull(user);
//    }
//
//}
