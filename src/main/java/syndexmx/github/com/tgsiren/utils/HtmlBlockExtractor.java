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
            String foundSection = findFirstSinceIndex(html, tag, nextIndex).toString();
            list.add(foundSection);
            position = nextIndex + foundSection.length();
            position++;

        }
        return list;
    }

    public static String findFirstSinceIndex(String html, String tag, int sinceIndex) {
        StringBuffer foundSection = new StringBuffer();
        int position = sinceIndex;
        if (html.indexOf("<" + tag + ">", position) >= 0 ||
                html.indexOf("<" + tag + " ", position) >= 0) {
            position = html.indexOf("<" + tag + ">", position);
            if (position < 0) {
                position = html.indexOf("<" + tag + " ", position);
                };
            if (html.indexOf("<" + tag + " ", position) > 0 &&
                    position > html.indexOf("<" + tag + " ", position)) {
                position = html.indexOf("<" + tag + " ", position);
            }
            int depth = 1;
            while (depth > 0 && position < html.length()) {
                int nextOpenning = Math.min(html.indexOf("<" + tag + ">", position),
                                html.indexOf("<" + tag + " ", position));
                int nextClosing = html.indexOf("</" + tag + ">", position);
                if (nextOpenning >= 0 && (nextOpenning < nextClosing ||
                        nextClosing < 0)) {
                    foundSection.append(html.substring(position, nextOpenning));
                    if (nextOpenning < html.length()) {
                        foundSection.append(html.charAt(nextOpenning));
                        position = nextOpenning;
                        foundSection.append(html.charAt(position));
                        position++;
                    }
                    depth++;
                } else {
                    if (nextClosing >= 0) {
                        String stringAddition = html.substring(position, nextClosing);
                        foundSection.append(stringAddition);
                        while (html.charAt(nextClosing) != '>') {
                            foundSection.append(html.charAt(nextClosing));
                            nextClosing++;
                        }
                        if (html.charAt(nextClosing) == '>') {
                            foundSection.append(html.charAt(nextClosing));
                            nextClosing++;
                        }
                        position = nextClosing;
                    } else {
                        foundSection.append(html.substring(position));
                        position = html.length();
                    }
                    depth--;
                }
            }
            return foundSection.toString();
        } else {
            System.out.println("Empty return");
            return "";
        }
    }

    public static List<String> extractAllClassedBlocks(String html, String tag, String className) {
        String classedTag = tag + " class=" + Q_MARK + className + Q_MARK;
        List<String> list = new ArrayList<>();
        int position = 0;
        while (position < html.length() && (html.indexOf("<" + classedTag, position) >= 0)) {
            int nextIndex = html.indexOf("<" + classedTag, position);
            boolean isNextTagOnly = false;
            if (nextIndex >= 0) {
                isNextTagOnly = true;
            }
            if (!isNextTagOnly) {
                continue;
            }
            String foundSection = findFirstSinceIndex(html, tag, nextIndex).toString();
            list.add(foundSection);
            position = nextIndex + foundSection.length();
            position++;
        }
        return list;
    }

}
