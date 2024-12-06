package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;
import syndexmx.github.com.tgsiren.services.filterservices.FilterService;

import java.io.IOException;
import java.util.List;

import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllClassedBlocks;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllTaggedBlocks;


@Service
@Slf4j
public class WebMonitorImpl implements WebMonitor {

    private Fetcher fetcher;
    private Integer updateInterval;
    private volatile boolean initiated = false;
    private ChannelService channelService;
    private FilterService filterService;

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                    @Autowired ChannelService channelService,
                   @Autowired FilterService filterService) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.channelService = channelService;
        this.filterService = filterService;
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
            List<String> divList =
                    extractAllClassedBlocks(receivedNewsFeed, "div", "tgme_widget_message_bubble");
            System.out.println(divList.toString());
            scanNewsFeed(url, receivedNewsFeed);
        }
    }

    private void scanNewsFeed(String url, String receivedNewsFeed) {
        log.info("Scanning " + url);
        List<String> filterList = filterService.listAllFilters().stream()
                .map(filterDto -> {
                        return filterDto.getFilterString();
                         })
                .toList();
        List<String> blocks =
                extractAllClassedBlocks(receivedNewsFeed, "div", "tgme_widget_message_bubble");
        List<String> filteredBlocks = blocks.stream()
                .filter(block -> {
                    for (String string : filterList) {
                        if (block.indexOf(string) >= 0) return true;
                    }
                    return false; // Temporarily Disabled
                })
                .toList();
        System.out.println(blocks.toString());
    };

    private void goToSleep() {
        try {
            Thread.sleep(updateInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
