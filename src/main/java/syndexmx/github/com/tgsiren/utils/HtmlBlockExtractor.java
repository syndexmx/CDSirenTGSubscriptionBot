package syndexmx.github.com.tgsiren.utils;

public class HtmlBlockExtractor {

    static char Q_MARK = '"';

    public static String extractTagedBlock(String html, String tag, String className) {
        StringBuilder extractedHtml = new StringBuilder();
        int cursorPosition = html.indexOf("<" + tag + " class=" + Q_MARK + className);
        if (cursorPosition < 0) return ""; // if tag not found - > return empty
        while (html.charAt(cursorPosition) != '>') {
            cursorPosition++;
        }
        int depth = 1;
        StringBuffer htmlToProcess = new StringBuffer(html.substring(cursorPosition++));
        cursorPosition = 0;
        while (depth > 0) {

        }

        return extractedHtml.toString();
    }
}
