package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

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
import syndexmx.github.com.tgsiren.utils.Colorer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void startMonitor() {
        if (initiated) return;
        initiated = true;
        log.info(Colorer.decorate("<white>Мониторинг запущен</>"));
        int cycle = 0;
        while (true) {
            try {
                if (cycle % 360 == 0) {
                    log.info(Colorer.decorate("<gray>Онлайн</>"));
                }
                //log.info(Colorer.decorate("<gray>Запланированный вызов</>"));
                scanWebSources();
            } catch (IOException e) {
                // throw new RuntimeException(e);
                log.error("Ошибка обращения к сети: " + e.getMessage());
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
            //log.info(Colorer.decorate("<gray>Сканирование каналов ..."));
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
            log.error(Colorer.decorate("<scarlet>Error occured" + exception.toString() + "</>"));
            exception.printStackTrace();
        }

    }

    private void scanBlockForFilterMatch(String url, String block) {
        //log.info(Colorer.decorate("<cyan>" + block + "</>"));
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
        //log.info(Colorer.decorate("<cyan>" + block + "</>"));
        String footerBlock = extractAllClassedBlocks(block.replace("+00:00","-GMT+03:00"),
                "a", "tgme_widget_message_date").toString();
        String footerText = "\n " + deTag(footerBlock + "GMT + 3:00 часа для МСК");
        String textBlock = deTag(extractAllClassedBlocks(block,
                "div", "tgme_widget_message_text js-message_text").toString());
        if (textBlock.length() > 4072) {
            textBlock = textBlock.substring(0, 4072);
        }
        //log.info(Colorer.decorate("<magenta>Выявлено совпадение с фильтром</>: <cyan>" + textBlock + "</>"));
        String text = textBlock + "\n" + footerText;
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
        //log.info(Colorer.decorate("<magenta>Новое сообщение</>: <cyan>" + feedMessage + "</>"));
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
