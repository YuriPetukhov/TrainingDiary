//package org.example.core.service;
//
//import org.example.core.domain.User;
//import org.example.core.domain.Workout;
//import org.example.core.enums.UserRole;
//import org.example.core.service.impl.UserServiceImpl;
//import org.example.core.service.impl.WorkoutServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//class WorkoutServiceImplTest {
//
//    private WorkoutServiceImpl workoutService;
//
//    @BeforeEach
//    void setUp() {
//        workoutService = new WorkoutServiceImpl();
//    }
//
//    @Test
//    @DisplayName("Проверка добавления тренировки")
//    void testAddWorkout() {
//
//        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
//        WorkoutServiceImpl workoutService = new WorkoutServiceImpl();
//        try {
//            Field userServiceField = WorkoutServiceImpl.class.getDeclaredField("userService");
//            userServiceField.setAccessible(true);
//            userServiceField.set(workoutService, userServiceImpl);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        User currentUser = mock(User.class);
//        doNothing().when(userServiceImpl).addWorkout(any(), any());
//        workoutService.addWorkout(currentUser, LocalDate.now(), "Running", 30, 200, new HashMap<>());
//
//        verify(userServiceImpl).addWorkout(any(), any());
//
//    }
//
//    @Test
//    @DisplayName("Проверка редактирования тренировки")
//    void testEditWorkout() {
//        User user = new User("username", "password", UserRole.USER);
//        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 200);
//        user.getWorkouts().put(workout.getId(), workout);
//
//        int id = workout.getId();
//        LocalDate newDate = LocalDate.now().plusDays(1);
//        String newType = "Плавание";
//        int newDuration = 45;
//        int newCaloriesBurned = 250;
//
//        workoutService.editWorkout(user, id, newDate, newType, newDuration, newCaloriesBurned);
//
//        Workout updatedWorkout = user.createFindByIdQuery(id);
//        assertEquals(newDate, updatedWorkout.getDate());
//        assertEquals(newType, updatedWorkout.getType());
//        assertEquals(newDuration, updatedWorkout.getDuration());
//        assertEquals(newCaloriesBurned, updatedWorkout.getCaloriesBurned());
//    }
//
//    @Test
//    @DisplayName("Проверка удаления тренировки")
//    void testDeleteWorkout() {
//        User user = new User("username", "password", UserRole.USER);
//        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 200);
//        user.getWorkouts().put(workout.getId(), workout);
//
//        Long id = workout.getId();
//
//        boolean result = workoutService.deleteWorkout(id);
//
//        assertTrue(result);
//        assertTrue(user.getWorkouts().isEmpty());
//    }
//
//
//    @Test
//    @DisplayName("Проверка получения общего количества сожженных калорий")
//    void testGetCaloriesBurnedTotal() {
//
//        User user = new User("username", "password", UserRole.USER);
//        Workout workout1 = new Workout(LocalDate.now(), "Бег", 30, 200);
//        Workout workout2 = new Workout(LocalDate.now().plusDays(1), "Плавание", 45, 250);
//        user.getWorkouts().put(workout1.getId(), workout1);
//        user.getWorkouts().put(workout2.getId(), workout2);
//
//        int totalCaloriesBurned = workoutService.getCaloriesBurnedTotal(user);
//
//        assertEquals(450, totalCaloriesBurned);
//    }
//
//    @Test
//    @DisplayName("Проверка получения количества сожженных калорий по дате")
//    void testGetCaloriesBurnedByDate() {
//
//        User user = new User("username", "password", UserRole.USER);
//        Workout workout1 = new Workout(LocalDate.now(), "Бег", 30, 200);
//        Workout workout2 = new Workout(LocalDate.now().plusDays(1), "Плавание", 45, 250);
//        user.getWorkouts().put(workout1.getId(), workout1);
//        user.getWorkouts().put(workout2.getId(), workout2);
//
//        Map<LocalDate, Integer> caloriesBurnedByDate = workoutService.getCaloriesBurnedByDate(user);
//
//        assertEquals(200, caloriesBurnedByDate.get(LocalDate.now()));
//        assertEquals(250, caloriesBurnedByDate.get(LocalDate.now().plusDays(1)));
//    }
//
//    @Test
//    @DisplayName("Проверка получения общей продолжительности тренировок")
//    void testGetTotalDuration() {
//
//        User user = new User("username", "password", UserRole.USER);
//        Workout workout = new Workout(LocalDate.now(), "Бег", 75, 200);
//        user.getWorkouts().put(workout.getId(), workout);
//
//        int totalDuration = workoutService.getTotalDuration(user);
//
//        assertEquals(75, totalDuration);
//    }
//
//}
