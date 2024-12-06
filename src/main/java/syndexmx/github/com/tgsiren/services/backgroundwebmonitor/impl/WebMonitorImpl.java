package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;
import syndexmx.github.com.tgsiren.services.feedmessagesservices.FeedMessageService;
import syndexmx.github.com.tgsiren.services.filterservices.FilterService;

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

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                    @Autowired ChannelService channelService,
                   @Autowired FilterService filterService,
                    @Autowired FeedMessageService feedMessageService) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.channelService = channelService;
        this.filterService = filterService;
        this.feedMessageService = feedMessageService;
    }

    @Override
    public void startMonitor() {
        if (initiated) return;
        initiated = true;
        log.info("Monitor service started");
        while (true) {
            try {
                scanWebSources();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            goToSleep();
        }
    }

    private void scanWebSources() throws IOException {
        log.info("scanWebSources");
        List<String> urlChannelSet = channelService.listAllChannels().stream()
                .map(channelDto -> {
                    return channelDto.getUrl();
                }).toList();
        for (String url : urlChannelSet) {
            String receivedNewsFeed = fetcher.getPage(url).toString();
            List<String> divList = extractAllClassedBlocks(receivedNewsFeed, "div",
                    "tgme_widget_message_wrap js-widget_message_wrap");
            divList.forEach(foundSection -> {
                scanBlockForFilterMatch(url, foundSection);});
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
        String footerBlock = extractAllClassedBlocks(block,
                "a", "tgme_widget_message_date").toString();
        String footerText = "\n\n **" + deTag(footerBlock) + "**";
        String textBlock = deTag(extractAllClassedBlocks(block,
                "div", "tgme_widget_message_text js-message_text").toString());
        if (textBlock.length() > 224) {
            textBlock = textBlock.substring(0, 224);
        }
        String text = textBlock + "\n" + footerText;
        FeedMessage feedMessage = FeedMessage.builder()
                .footer(footerBlock)
                .owner(url)
                .text(text)
                .build();
        Optional<FeedMessage> savedMessage = feedMessageService.addMessage(feedMessage);
        if (savedMessage.isEmpty()) {
            return;
        }
        notifyAllInterested(url, savedMessage.get());
    };

    private void notifyAllInterested(String url, FeedMessage feedMessage) {
        // TODO
        log.info("New message \n\n" + feedMessage.toString() + "\n\n");
    }

    private void goToSleep() {
        try {
            Thread.sleep(updateInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
