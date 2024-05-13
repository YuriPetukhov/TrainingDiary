package org.example.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.User;
import org.example.core.domain.Workout;
import org.example.core.enums.UserRole;
import org.example.core.repository.service_response_builder.impl.UserRepositoryImpl;
import org.example.core.service.UserService;
import org.example.logger.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с пользователями и тренировками.
 */
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
    private final Logger logger = new Logger();

    /**
     * Добавляет нового пользователя.
     *
     * @param username имя пользователя
     * @param password пароль
     * @param role роль пользователя
     * @return созданный пользователь
     */

    @Override
    public User addUser(String username, String password, UserRole role) {
        try {
            Optional<User> existingUser = userRepositoryImpl.findByName(username);
            if (existingUser.isPresent()) {
                System.out.println("Пользователь с таким именем уже существует. Пожалуйста, выберите другое имя пользователя.");
                return null;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);

            return userRepositoryImpl.save(user);
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
            return null;
        }
    }


    @Override
    public User updateUser(User user) {
        userRepositoryImpl.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepositoryImpl.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получает список всех пользователей.
     *
     * @return список пользователей
     */
    public List<User> getAllUsers() {
        try {
            return userRepositoryImpl.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Добавляет тренировку пользователю.
     *
     * @param newWorkout тренировка
     * @param id текущего пользователя
     */
    @Override
    public void addWorkout(Workout newWorkout, Long id) {
        User user = getUserById(id);
        if (user == null) {
            System.out.println("Пользователь не найден.");
            return;
        }

        List<Workout> workouts = user.getWorkouts();
        for (Workout existingWorkout : workouts) {
            if (existingWorkout.getDate().equals(newWorkout.getDate()) && existingWorkout.getConcreteWorkoutTypeId().equals(newWorkout.getConcreteWorkoutTypeId())) {
                System.out.println("Тренировка этого типа уже добавлена на эту дату.");
                return;
            }
        }
        user.setWorkouts(workouts);
        userRepositoryImpl.save(user);
        System.out.println("Тренировка добавлена");
        logger.log("Добавление тренировки", user.getUsername(), true);
    }


    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id пользователя
     * @return пользователь или `null`, если пользователь не найден
     */
    public User getUserById(Long id) {
        Optional<User> optionalUser = null;
        try {
            optionalUser = userRepositoryImpl.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalUser.orElse(null);

    }

    public Optional<User> findByUsername(String username) {
        try {
            return userRepositoryImpl.findByName(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
