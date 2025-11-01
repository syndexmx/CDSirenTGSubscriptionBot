package syndexmx.github.com.tgsiren.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public static void info(String message){
        final String fullLogLine = getCurrentTimestamp() + message;
        System.out.println(fullLogLine);
    }

    public static void error(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.red(message);
        System.out.println(fullLogLine);
    }

    public static void warn(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.yellow(message);
        System.out.println(fullLogLine);
    }

    public static void debug(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.cyan(message);
        System.out.println(fullLogLine);
    }

    public static void trace(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.magenta(message);
        System.out.println(fullLogLine);
    }

    public static void fatal(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.scarlet(message);
        System.out.println(fullLogLine);
    }

    public static void blue(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.blue(message);
        System.out.println(fullLogLine);
    }

    public static void green(String message){
        final String fullLogLine = getCurrentTimestamp() + Crayon.green(message);
        System.out.println(fullLogLine);
    }

    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestampString = LocalDateTime.now().format(formatter);
        String decoratedTimestamp = Crayon.gray(timestampString) + " ";
        return decoratedTimestamp;
    }
}