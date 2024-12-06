package syndexmx.github.com.tgsiren.utils;

import java.util.ArrayList;
import java.util.List;

public class oldHtmlBlockExtractor {

    static char Q_MARK = '"';

    public static String extractTaggedBlock(String html, String tag, String className) {
        StringBuilder extractedHtml = new StringBuilder();
        int cursorPosition = html.indexOf("<" + tag + " class=" + Q_MARK + className);
        if (cursorPosition < 0) return ""; // if tag not found - > return empty
        while (html.charAt(cursorPosition) != '>') {
            cursorPosition++;
        }
        int depth = 1;
        StringBuffer htmlToProcess = new StringBuffer(html.substring(cursorPosition + 1));
        while (depth >= 0) {
            if (htmlToProcess.indexOf("<" + tag) >= 0)
                if (htmlToProcess.indexOf("<" + tag) < htmlToProcess.indexOf("</" + tag)) {
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf("<" + tag)));
                    htmlToProcess.delete(0, htmlToProcess.indexOf("<" + tag));
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf(">")));
                    htmlToProcess.delete(0, htmlToProcess.indexOf(">"));
                    depth++;
                } else {
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf("</" + tag)));
                    htmlToProcess.delete(0, htmlToProcess.indexOf("</" + tag) + 1);
                    htmlToProcess.delete(0, 1);
                    depth--;
                }
        }
        return extractedHtml.toString();
    }

    public static String extractTaggedBlock(String html, String tag) {
        StringBuilder extractedHtml = new StringBuilder();
        int cursorPosition = html.indexOf("<" + tag);
        if (cursorPosition < 0) return ""; // if tag not found - > return empty
        while (html.charAt(cursorPosition) != '>') {
            cursorPosition++;
        }
        int depth = 1;
        StringBuffer htmlToProcess = new StringBuffer(html.substring(cursorPosition + 1));
        while (depth > 0) {
            if (htmlToProcess.indexOf("<" + tag) >= 0)
                if (htmlToProcess.indexOf("<" + tag) < htmlToProcess.indexOf("</" + tag)) {
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf("<" + tag)));
                    htmlToProcess.delete(0, htmlToProcess.indexOf("<" + tag));
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf(">")));
                    htmlToProcess.delete(0, htmlToProcess.indexOf(">"));
                    depth++;
                } else {
                    extractedHtml.append(htmlToProcess.substring(0, htmlToProcess.indexOf("</" + tag)));
                    htmlToProcess.delete(0, htmlToProcess.indexOf("</" + tag));
                    htmlToProcess.delete(0, 1);
                    depth--;
                }
        }
        return extractedHtml.toString();
    }

    public static List<String> extractAllTaggedBlocks(String html, String tag, String className) {
        List<String> list = new ArrayList<>();
        StringBuffer htmlBuffer = new StringBuffer(html);
        while (htmlBuffer.indexOf("<" + tag + " class=" + Q_MARK + className) >= 0) {
            String extractedBlock = extractTaggedBlock(html, tag, className);
            list.add(extractedBlock);
            int startIndex = htmlBuffer.indexOf("<" + tag + " class=" + Q_MARK + className);
            htmlBuffer.delete(startIndex, startIndex + extractedBlock.length());
        }
        return list;
    }
}