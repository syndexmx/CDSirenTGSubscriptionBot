package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.webfetcher.Fetcher;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.NotificationService;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;
import syndexmx.github.com.tgsiren.services.feedmessagesservices.FeedMessageService;
import syndexmx.github.com.tgsiren.services.filterservices.FilterService;
import syndexmx.github.com.tgsiren.utils.Crayon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.deTag;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllClassedBlocks;

@Service
@Slf4j
public class WebMonitorImpl implements WebMonitor {

    private Fetcher fetcher;
    private Integer updateInterval;
    private volatile boolean initiated = false;
    private ChannelService channelService;
    private FilterService filterService;
    private FeedMessageService feedMessageService;
    private NotificationService notificationService;

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                   @Autowired ChannelService channelService,
                   @Autowired FilterService filterService,
                   @Autowired NotificationService notificationService,
                   @Autowired FeedMessageService feedMessageService) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.channelService = channelService;
        this.filterService = filterService;
        this.feedMessageService = feedMessageService;
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void init() {
        Thread thread = new Thread(() -> startMonitor());
        thread.start();

    }

    @Override
    public void startMonitor() {
        if (initiated) return;
        initiated = true;
        log.info(Crayon.white("Monitoring is running"));
        FeedMessage startingFeedMessage = FeedMessage.builder()
                .text("Сервис перезапущен и снова онлайн. \n Вы снова будете получать уведомления. \n" +
                        LocalDateTime.now().toString().replace("T", " "))
                .footer(LocalDateTime.now().toString())
                .build();
        notificationService.notifyAllInterested("Tg Bot", startingFeedMessage );
        int cycle = 0;
        while (true) {
            try {
                if (cycle % 360 == 0) {
                    log.info(Crayon.gray("On-line"));
                }
                //log.info(Crayon.gray("Запланированный вызов"));
                scanWebSources();
            } catch (IOException e) {
                // throw new RuntimeException(e);
                log.error("HTTPS connection error: " + e.getMessage());
                try {
                    Thread.sleep(60_000);
                } catch (Exception sleepException) {
                }
                //e.printStackTrace();
            }
            goToSleep();
            cycle++;
        }
    }

    private void scanWebSources() throws IOException {
        try {
            List<String> urlChannelSet = channelService.listAllChannels().stream()
                    .map(channelDto -> {
                        return channelDto.getUrl();
                    }).toList();
            for (String url : urlChannelSet) {
                String receivedNewsFeed = fetcher.getPage(url).toString();
                List<String> divList = extractAllClassedBlocks(receivedNewsFeed, "div",
                        "tgme_widget_message_wrap js-widget_message_wrap");
                divList.forEach(foundSection -> {
                    scanBlockForFilterMatch(url, foundSection);
                });
            }
        } catch (RuntimeException exception) {
            log.error(Crayon.scarlet("Error occured" + exception.toString()));
            exception.printStackTrace();
        }

    }

    private void scanBlockForFilterMatch(String url, String block) {
        List<String> filterList = filterService.listAllFilters().stream()
                .map(filterDto -> {
                    return filterDto.getFilterString();
                })
                .toList();
        boolean match = false;
        for (String filter : filterList) {
            if (block.contains(filter)) {
                match = true;
            }
        }
        if (!match) return;
        //String footerBlock = extractAllClassedBlocks(block.replace("+00:00","-GMT+03:00"),
        //        "a", "tgme_widget_message_date").toString();
        String footerBlock = extractAllClassedBlocks(block,"a", "tgme_widget_message_date").toString();
        Pattern pattern = Pattern.compile("href=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(footerBlock);
        String footerText = "";
        if (matcher.find()) {
            String link = matcher.group(1);
            footerText = link;
        }
        Pattern dateTimePattern = Pattern.compile("datetime=\"([^\"]+)\"");
        Matcher dateTimeMatcher = dateTimePattern.matcher(footerBlock);

        if (dateTimeMatcher.find()) {
            String datetimeString = dateTimeMatcher.group(1);

            // Parse to LocalDateTime and convert to GMT+3
            LocalDateTime utcDateTime = LocalDateTime.parse(datetimeString, DateTimeFormatter.ISO_DATE_TIME);
            ZonedDateTime gmtPlus3DateTime = utcDateTime.atZone(ZoneOffset.UTC)
                    .withZoneSameInstant(ZoneId.of("Europe/Moscow")); // GMT+3

            footerText += "\n" +  gmtPlus3DateTime.toString()
                    .replace("T", " ")
                    .replace("+", "  Moscow/МСК (UTC+")
                    .replace("[Europe/Moscow]", ")");
        }
        String textBlock = deTag(extractAllClassedBlocks(block,
                "div", "tgme_widget_message_text js-message_text").toString());
        if (textBlock.length() > 4072) {
            textBlock = textBlock.substring(0, 4072);
        }
        String text = textBlock + "\n\n" + footerText;
        //text = text.replaceAll("\\s+", " ");
        FeedMessage feedMessage = FeedMessage.builder()
                .footer(footerBlock
                        .replace(" class=" + '"' + "tgme_widget_message_date" + '"', "")
                        .replace(" class=" + '"' + "time" + '"', ""))
                .owner(url)
                .text(text)
                .build();
        Optional<FeedMessage> savedMessage = feedMessageService.addMessage(feedMessage);
        if (savedMessage.isEmpty()) {
            return;
        }
        notificationService.notifyAllInterested(url, savedMessage.get());
    };


    private void goToSleep() {
        try {
            Thread.sleep(updateInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}