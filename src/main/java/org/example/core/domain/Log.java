package org.example.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Log extends Entity{
    private String action;
    private String user;
    private LocalDateTime timestamp;
    private boolean success;

    public Log(String action, String user, LocalDateTime timestamp, boolean success) {
        this.action = action;
        this.user = user;
        this.timestamp = timestamp;
        this.success = success;
    }
}
