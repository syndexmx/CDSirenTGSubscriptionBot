package syndexmx.github.com.tgsiren.utils;

public class HtmlBlockCutter {

    public static String cutTagedBlockOut(String html, String tag) {
        StringBuffer trimmed = new StringBuffer(html);
        while (trimmed.indexOf("<" + tag) >= 0) {
            int startingPosition = trimmed.indexOf("<" + tag);
            int endingPosition = trimmed.indexOf("</" + tag);
            while (trimmed.charAt(endingPosition++) != '>') {
            }
            trimmed.delete(startingPosition, endingPosition);
        }
        return trimmed.toString();
    }
}
