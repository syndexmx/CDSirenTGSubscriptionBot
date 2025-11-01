package syndexmx.github.com.tgsiren.utils;

public enum AnsiColors {
    ANSI_RESET("\u001B[0m"),

    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_GOLD("\u001B[33m"),
    ANSI_INDIGO("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_GRAY("\u001B[37m"),
    ANSI_DARK("\u001B[90m"),

    ANSI_SCARLET("\033[0;91m"),
    ANSI_LIME("\033[0;92m"),
    ANSI_YELLOW("\033[0;93m"),
    ANSI_BLUE("\033[0;94m"),
    ANSI_MAGENTA("\033[0;95m"),
    ANSI_AZURE("\033[0;96m"),
    ANSI_WHITE("\033[0;97m"),
    ANSI_SILVER("\033[0;37m"),

    ANSI_BG_BLACK("\u001B[40m"),
    ANSI_BG_RED("\u001B[41m"),
    ANSI_BG_GREEN("\u001B[42m"),
    ANSI_BG_GOLD("\u001B[43m"),
    ANSI_BG_INDIGO("\u001B[44m"),
    ANSI_BG_PURPLE("\u001B[45m"),
    ANSI_BG_CYAN("\u001B[46m"),
    ANSI_BG_GRAY("\u001B[47m"),
    ANSI_BG_DARK("\u001B[100m"),

    ANSI_BG_SCARLET("\033[0;101m"),
    ANSI_BG_LIME("\033[0;102m"),
    ANSI_BG_YELLOW("\033[0;103m"),
    ANSI_BG_BLUE("\033[0;104m"),
    ANSI_BG_MAGENTA("\033[0;105m"),
    ANSI_BG_AZURE("\033[0;106m"),
    ANSI_BG_WHITE("\033[0;107m"),

    ANSI_BOLD("\u001B[1m"),
    ANSI_DIM("\u001B[2m"),
    ANSI_ITALIC("\u001B[3m"),
    ANSI_UNDERLINE("\u001B[4m"),
    ANSI_BLINK("\u001B[5m"),
    ANSI_INVERSE("\u001B[7m"),
    ANSI_HIDDEN("\u001B[8m"),
    ANSI_STRIKETHROUGH("\u001B[9m");

    private final String value;

    public String getValue() {
        return value;
    }

    AnsiColors(String value) {
        this.value = value;
    }
}