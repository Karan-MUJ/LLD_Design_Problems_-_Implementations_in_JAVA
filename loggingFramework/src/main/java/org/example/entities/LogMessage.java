package org.example.entities;

import org.example.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Date;

public class LogMessage {
    private final String message;
    private final LocalDateTime timestamp;
    private final LogLevel level;
    private final String threadName;
    private final String loggerName;

    public LogMessage(String message, LogLevel level, String loggerName) {
        this.message = message;
        this.level = level;
        this.threadName = Thread.currentThread().getName();
        this.timestamp = LocalDateTime.now();
        this.loggerName = loggerName;
    }

    public String getMessage() { return message;}
    public LocalDateTime getTimestamp() { return timestamp; }
    public LogLevel getLevel() { return level; }
    public String getThreadName() { return threadName; }
    public String getLoggerName() { return loggerName; }
}
