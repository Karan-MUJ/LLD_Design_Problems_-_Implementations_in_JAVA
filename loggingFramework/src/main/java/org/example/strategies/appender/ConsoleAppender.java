package org.example.strategies.appender;

import org.example.entities.LogMessage;
import org.example.strategies.formatter.LogFormatter;

public class ConsoleAppender implements LogAppender {
    private LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logMessage.getMessage());
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return this.formatter;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}
