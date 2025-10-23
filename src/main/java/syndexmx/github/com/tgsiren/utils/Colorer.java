package syndexmx.github.com.tgsiren.utils;

public class Colorer {

    public static String decorate(String message) {
        return message
                .replaceAll("</>", AnsiColors.ANSI_RESET.getValue())
                .replaceAll("<white>", AnsiColors.BRIGHT_WHITE.getValue())
                .replaceAll("<green>", AnsiColors.ANSI_GREEN.getValue())
                .replaceAll("<lime>", AnsiColors.BRIGHT_GREEN.getValue())
                .replaceAll("<indigo>", AnsiColors.ANSI_BLUE.getValue())
                .replaceAll("<blue>", AnsiColors.BRIGHT_BLUE.getValue())
                .replaceAll("<yellow>", AnsiColors.ANSI_YELLOW.getValue())
                .replaceAll("<bright-yellow>", AnsiColors.BRIGHT_YELLOW.getValue())
                .replaceAll("<purple>", AnsiColors.ANSI_PURPLE.getValue())
                .replaceAll("<magenta>", AnsiColors.BRIGHT_PURPLE.getValue())
                .replaceAll("<scarlet>", AnsiColors.BRIGHT_RED.getValue())
                .replaceAll("<gray>", AnsiColors.ANSI_GRAY.getValue())
                .replaceAll("<bright-gray>", AnsiColors.BRIGHT_GRAY.getValue())
                .replaceAll("<cyan>", AnsiColors.ANSI_CYAN.getValue())
                .replaceAll("<bright-cyan>", AnsiColors.BRIGHT_CYAN.getValue())
                .replaceAll("<red>", AnsiColors.ANSI_RED.getValue()) + AnsiColors.ANSI_RESET.getValue();
    }
}
