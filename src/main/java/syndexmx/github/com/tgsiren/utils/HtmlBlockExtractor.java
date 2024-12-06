package syndexmx.github.com.tgsiren.utils;

import java.util.ArrayList;
import java.util.List;

public class HtmlBlockExtractor {


    static char Q_MARK = '"';


    public static List<String> extractAllTaggedBlocks(String html, String tag) {
        List<String> list = new ArrayList<>();
        int position = 0;
        while (position < html.length() && (html.indexOf("<" + tag + ">", position) >= 0) ||
                html.indexOf("<" + tag + " ", position) >= 0) {
            int nextTagOnlyIndex = html.indexOf("<" + tag + ">", position);
            int nextTagWithProps = html.indexOf("<" + tag + " ", position);
            boolean isNextTagOnly = false;
            if (nextTagOnlyIndex >= 0 && nextTagOnlyIndex < nextTagWithProps) {
                isNextTagOnly = true;
            }
            boolean isNextTagWithProps = false;
            if (nextTagWithProps >= 0 && (nextTagWithProps < nextTagOnlyIndex) ||
                    nextTagOnlyIndex < 0) {
                isNextTagWithProps = true;
            }
            if (!isNextTagOnly && !isNextTagWithProps) {
                continue;
            }
            int nextIndex = nextTagOnlyIndex;
            if (isNextTagWithProps) {
                nextIndex = nextTagWithProps;
            }
            String foundSection = extractFirstSinceIndex(html, tag, nextIndex).toString();
            list.add(foundSection);
            position = nextIndex + foundSection.length();
            position++;

        }
        return list;
    }

    public static String extractFirstSinceIndex(String html, String tag, int sinceIndex) {
        StringBuffer foundSection = new StringBuffer();
        int position = sinceIndex;
        int depth = 0;
        do {
            int nextOpenning = html.indexOf("<" + tag, position + 1);
            int nextClosing = html.indexOf("</" + tag + ">", position );
            if (nextOpenning >= 0 & nextOpenning < nextClosing) {
                foundSection.append(html.substring(position, nextOpenning));
                position = nextOpenning;
                depth++;
            } else {
                if (nextClosing >= 0) {
                    String stringBeforeClosingTag = html.substring(position, nextClosing);
                    foundSection.append(stringBeforeClosingTag);
                    while (html.charAt(nextClosing) != '>') {
                        foundSection.append(html.charAt(nextClosing++));
                    }
                    if (html.charAt(nextClosing) == '>') {
                        foundSection.append(html.charAt(nextClosing++));
                    }
                    position = nextClosing;
                    depth--;
                } else {
                    foundSection.append(html.substring(position));
                    position = html.length();
                    depth = 0;
                }
            }
        } while (depth > 0 & position < html.length());
        return foundSection.toString();
    }

    public static List<String> extractAllClassedBlocks(String html, String tag, String className) {
        String classedTag = tag + " class=" + Q_MARK + className + Q_MARK;
        List<String> list = new ArrayList<>();
        int position = 0;
        while (position < html.length() & (html.indexOf("<" + classedTag, position) >= 0)) {
            int nextIndex = html.indexOf("<" + classedTag, position);
            String foundSection = extractFirstSinceIndex(html, tag, nextIndex).toString();
            list.add(foundSection);
            position = nextIndex + foundSection.length();
        }
        return list;
    }

    public static List<String> extractAllTaggedBlocksWithProperty(String html, String tag, String property) {
        List<String> list = new ArrayList<>();
            //TODO
        return list;
    }

    public static String deTag(String html) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean tagOn = false;
        for (int i = 0; i < html.length(); i++) {
            if (!tagOn) {
                if (html.charAt(i) == '<') {
                    tagOn = true;
                } else {
                    stringBuffer.append(html.charAt(i));
                }
            } else {
                if (html.charAt(i) == '>') {
                    tagOn = false;
                }
            }

        }
        return stringBuffer.toString();
    }

}
