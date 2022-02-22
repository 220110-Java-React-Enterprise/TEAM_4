package com.revature.team4.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLogger {
    private static ExceptionLogger exceptionLogger;
    private static String filePath;
    private static int stackTraceSize;
    
    public ExceptionLogger() {
        filePath = "src/main/resources/Exception_Logs/";
        stackTraceSize = 10;
    }

    public static ExceptionLogger getExceptionLogger() {
        if (exceptionLogger == null) {
            exceptionLogger = new ExceptionLogger();
        }

        return exceptionLogger;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static int getStackTraceSize() {
        return stackTraceSize;
    }

    public static void setFilePath(String filePath) {
        ExceptionLogger.filePath = filePath;
    }

    public static void setStackTraceSize(int stackTraceSize) {
        ExceptionLogger.stackTraceSize = stackTraceSize;
    }

    public void log(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(exceptionLogger.getTimestamp())
                .append(" - ")
                .append(e.getMessage())
                .append("\n")
                .append(exceptionLogger.formatStackTrace(e));
        writeToLog(stringBuilder.toString());
    }

    public void log(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(exceptionLogger.getTimestamp())
                .append(" - ")
                .append(s);
        writeToLog(stringBuilder.toString());
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]"));
    }

    private String getFileName() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".log";
    }

    private String formatStackTrace(Exception e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stackTraceElements.length && i < stackTraceSize; i++) {
            stringBuilder.append("\t");
            stringBuilder.append(stackTraceElements[i]);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private void writeToLog(String logText) {
        String fileName = getFilePath() + getFileName();

        //Attempt to write to a log file in the filePath directory
        try (Writer fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(logText);

            //Attempt to write to a log in the overall project directory if filePath directory not found
        } catch (IOException e) {
            try (Writer fileWriterBackUp = new FileWriter(getFileName(), true)) {
                fileWriterBackUp.write(logText);

                //Print stack trace if it all goes horribly wrong
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
