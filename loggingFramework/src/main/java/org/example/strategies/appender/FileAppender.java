package org.example.strategies.appender;

import org.example.entities.LogMessage;
import org.example.strategies.formatter.LogFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender {
    private LogFormatter formatter;
    private FileWriter fileWriter;

    public FileAppender(String filePath, LogFormatter formatter) {
        this.formatter = formatter;
        try {
            this.fileWriter = new FileWriter(filePath, true);
        } catch (IOException ioException) {
            System.out.println("Failed to create writer for file logs, exception: " + ioException.getMessage());
        }
    }

    @Override
    public void append(LogMessage logMessage) {
        try {
            fileWriter.write(formatter.format(logMessage) + "\n");
            fileWriter.flush();
        } catch (IOException ioException) {
            System.out.println("Failed to write log message to file, exception: " + ioException.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("Failed to close logs file, exception: " + ioException.getMessage());
        }
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}
