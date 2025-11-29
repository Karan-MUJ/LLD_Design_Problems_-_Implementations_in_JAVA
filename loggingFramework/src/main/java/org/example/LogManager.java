package org.example;

import org.example.strategies.appender.LogAppender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    static LogManager instance = null;
    private AsyncLogProcessor processor;
    Logger rootLogger;
    private final Map<String, Logger> loggers = new ConcurrentHashMap<>();

    private LogManager() {
        processor = new AsyncLogProcessor();
        rootLogger = new Logger("root", null);
        loggers.put("root", rootLogger);
    }

    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    public Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, this::createLogger);
    }

    public Logger createLogger(String name) {
        if (name == "root") {
            return rootLogger;
        }
        int lastDotIndex = name.lastIndexOf('.');
        String parentName = (lastDotIndex > -1) ? name.substring(0, lastDotIndex) : "root";
        Logger parentLogger = getLogger(parentName);
        return new Logger(name, parentLogger);
    }

    public AsyncLogProcessor getProcessor() {
        return processor;
    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public void shutDown() {
        processor.stop();
        loggers.values().stream().flatMap(logger -> logger.getAppenders().stream()).distinct()
                .forEach(LogAppender::close);
        System.out.println("Logging framework shutdown complete.");
    }
}
