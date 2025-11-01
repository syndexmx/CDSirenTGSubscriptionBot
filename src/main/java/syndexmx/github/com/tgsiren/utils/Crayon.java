package syndexmx.github.com.tgsiren.utils;

public class Crayon {

    public static String black(String text) {
        return AnsiColors.ANSI_BLACK.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String red(String text) {
        return AnsiColors.ANSI_RED.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String green(String text) {
        return AnsiColors.ANSI_GREEN.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String gold(String text) {
        return AnsiColors.ANSI_GOLD.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String indigo(String text) {
        return AnsiColors.ANSI_INDIGO.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String purple(String text) {
        return AnsiColors.ANSI_PURPLE.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String cyan(String text) {
        return AnsiColors.ANSI_CYAN.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String gray(String text) {
        return AnsiColors.ANSI_GRAY.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String dark(String text) {
        return AnsiColors.ANSI_DARK.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String scarlet(String text) {
        return AnsiColors.ANSI_SCARLET.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String lime(String text) {
        return AnsiColors.ANSI_LIME.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String yellow(String text) {
        return AnsiColors.ANSI_YELLOW.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String blue(String text) {
        return AnsiColors.ANSI_BLUE.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String magenta(String text) {
        return AnsiColors.ANSI_MAGENTA.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String azure(String text) {
        return AnsiColors.ANSI_AZURE.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String white(String text) {
        return AnsiColors.ANSI_WHITE.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String silver(String text) {
        return AnsiColors.ANSI_SILVER.getValue() + text + AnsiColors.ANSI_RESET.getValue();
    }

    public static String deColor(String colorDecoratedText) {
        return colorDecoratedText.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}