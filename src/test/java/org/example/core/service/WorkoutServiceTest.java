package org.example.core.service;

import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutServiceTest {

    @Test
    @DisplayName("Проверка добавления тренировки")
    void testAddWorkout() {

        User user = new User("username", "password", false);

        LocalDate date = LocalDate.now();
        String type = "Бег";
        int duration = 30;
        int caloriesBurned = 200;
        Map<String, Object> additionalParameters = new HashMap<>();

        WorkoutService workoutService = new WorkoutService();
        workoutService.addWorkout(user, date, type, duration, caloriesBurned, additionalParameters);

        assertEquals(1, user.getWorkouts().size());
    }


    @Test
    @DisplayName("Проверка редактирования тренировки")
    void testEditWorkout() {
        User user = new User("username", "password", false);
        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 200);
        user.getWorkouts().add(workout);

        int id = workout.getId();
        LocalDate newDate = LocalDate.now().plusDays(1);
        String newType = "Плавание";
        int newDuration = 45;
        int newCaloriesBurned = 250;

        WorkoutService workoutService = new WorkoutService();
        workoutService.editWorkout(user, id, newDate, newType, newDuration, newCaloriesBurned);

        Workout updatedWorkout = user.getWorkoutById(id);
        assertEquals(newDate, updatedWorkout.getDate());
        assertEquals(newType, updatedWorkout.getType());
        assertEquals(newDuration, updatedWorkout.getDuration());
        assertEquals(newCaloriesBurned, updatedWorkout.getCaloriesBurned());
    }

    @Test
    @DisplayName("Проверка удаления тренировки")
    void testDeleteWorkout() {
        User user = new User("username", "password", false);
        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 200);
        user.getWorkouts().add(workout);

        int id = workout.getId();

        WorkoutService workoutService = new WorkoutService();
        boolean result = workoutService.deleteWorkout(user, id);

        assertTrue(result);
        assertTrue(user.getWorkouts().isEmpty());
    }


    @Test
    @DisplayName("Проверка получения общего количества сожженных калорий")
    void testGetCaloriesBurnedTotal() {

        User user = new User("username", "password", false);
        Workout workout1 = new Workout(LocalDate.now(), "Бег", 30, 200);
        Workout workout2 = new Workout(LocalDate.now().plusDays(1), "Плавание", 45, 250);
        user.getWorkouts().add(workout1);
        user.getWorkouts().add(workout2);

        WorkoutService workoutService = new WorkoutService();
        int totalCaloriesBurned = workoutService.getCaloriesBurnedTotal(user);

        assertEquals(450, totalCaloriesBurned);
    }

    @Test
    @DisplayName("Проверка получения количества сожженных калорий по дате")
    void testGetCaloriesBurnedByDate() {

        User user = new User("username", "password", false);
        Workout workout1 = new Workout(LocalDate.now(), "Бег", 30, 200);
        Workout workout2 = new Workout(LocalDate.now().plusDays(1), "Плавание", 45, 250);
        user.getWorkouts().add(workout1);
        user.getWorkouts().add(workout2);

        WorkoutService workoutService = new WorkoutService();
        Map<LocalDate, Integer> caloriesBurnedByDate = workoutService.getCaloriesBurnedByDate(user);

        assertEquals(200, caloriesBurnedByDate.get(LocalDate.now()));
        assertEquals(250, caloriesBurnedByDate.get(LocalDate.now().plusDays(1)));
    }

    @Test
    @DisplayName("Проверка наличия тренировки по идентификатору")
    void testCheckWorkoutById() {
        User user = new User("username", "password", false);
        Workout workout = new Workout(LocalDate.now(), "Бег", 30, 200);
        user.getWorkouts().add(workout);

        int id = workout.getId();

        WorkoutService workoutService = new WorkoutService();
        boolean result = workoutService.checkWorkoutById(user, id);

        assertTrue(result);
    }


    @Test
    @DisplayName("Проверка получения общей продолжительности тренировок")
    void testGetTotalDuration() {

        User user = new User("username", "password", false);
        Workout workout = new Workout(LocalDate.now(), "Бег", 75, 200);
        user.getWorkouts().add(workout);

        WorkoutService workoutService = new WorkoutService();
        int totalDuration = workoutService.getTotalDuration(user);

        assertEquals(75, totalDuration);
    }

}
