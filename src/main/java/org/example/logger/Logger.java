package org.example.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Сервис для ведения логов.
 */
public class Logger {

    private static final String LOG_FILE_PATH = "application_log.txt";

    /**
     * Записывает лог-запись в файл.
     *
     * @param action  действие, которое было выполнено
     * @param user    пользователь, который выполнил действие
     * @param success признак успешного выполнения действия
     */
    public static void log(String action, String user, boolean success) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String logEntry = String.format("Action: %s | User: %s | Date: %s | Success: %s",
                    action, user, LocalDateTime.now(), success ? "SUCCESS" : "FAIL");
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
