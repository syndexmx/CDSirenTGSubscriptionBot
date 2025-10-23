package syndexmx.github.com.tgsiren.utils;

public enum AnsiColors {
    ANSI_RESET ("\u001B[0m"),
    ANSI_BLACK ("\u001B[30m"),
    ANSI_RED ("\u001B[31m"),
    ANSI_GREEN ("\u001B[32m"),
    ANSI_YELLOW ("\u001B[33m"),
    ANSI_BLUE ("\u001B[34m"),
    ANSI_PURPLE ("\u001B[35m"),
    ANSI_CYAN ("\u001B[36m"),
    ANSI_WHITE ("\u001B[37m"),
    ANSI_GRAY ("\u001B[90m"),

    BRIGHT_BLACK("\033[0;90m"),  // BLACK
    BRIGHT_RED("\033[0;91m"),  // RED
    BRIGHT_GREEN("\033[0;92m"),  // GREEN
    BRIGHT_YELLOW("\033[0;93m"), // YELLOW
    BRIGHT_BLUE("\033[0;94m"),   // BLUE
    BRIGHT_PURPLE("\033[0;95m"), // PURPLE
    BRIGHT_CYAN("\033[0;96m"),   // CYAN
    BRIGHT_WHITE("\033[0;97m"),  // WHITE;
    BRIGHT_GRAY("\033[0;37m");  // WHITE;

    private String value;

    public String getValue() {
        return value;
    }

    AnsiColors(String value) {
        this.value = value;
    }
}