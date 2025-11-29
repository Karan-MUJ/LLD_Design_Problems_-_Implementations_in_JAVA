package org.example;

import org.example.entities.LogMessage;
import org.example.enums.LogLevel;
import org.example.strategies.appender.LogAppender;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final String name;
    private LogLevel level;
    private final Logger parent;
    private final List<LogAppender> appenders;
    private boolean additivity = true;

    public Logger(String name, Logger parent) {
        this.name = name;
        this.parent = parent;
        this.appenders = new ArrayList<>();
    }

    Logger getParent() {
        return parent;
    }

    public void addAppender(LogAppender appender) {
        this.appenders.add(appender);
    }

    public List<LogAppender> getAppenders() {
        return appenders;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

    public LogLevel getEffectiveLevel() {
        for (Logger logger = this; logger != null; logger = logger.getParent()) {
            LogLevel currentLevel = logger.level;
            if (currentLevel != null) {
                return currentLevel;
            }
        }
        return LogLevel.DEBUG;
    }

    private void log(LogLevel messageLevel, String message) {
        if (messageLevel.isGreaterThanOrEqual(getEffectiveLevel())) {
            callAppenders(new LogMessage(message, messageLevel, name));
        }
    }

    private void callAppenders(LogMessage logMessage) {
        if (!appenders.isEmpty()) {
            // Assuming there's only one processor
            LogManager.getInstance().getProcessor().process(logMessage, this.appenders);
        }
        if (additivity && parent != null) {
            parent.callAppenders(logMessage);
        }
    }

    public void info(String message) { log(LogLevel.INFO, message); }

    public void debug(String message) { log(LogLevel.DEBUG, message); }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}
