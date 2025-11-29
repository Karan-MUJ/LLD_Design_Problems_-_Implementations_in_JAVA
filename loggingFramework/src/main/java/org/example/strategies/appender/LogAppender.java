package org.example.strategies.appender;

import org.example.entities.LogMessage;
import org.example.strategies.formatter.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter formatter);
}
