package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.config.MessageFilterConfig;
import syndexmx.github.com.tgsiren.config.WebSourcesConfig;
import syndexmx.github.com.tgsiren.controllers.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.controllers.webmonitor.fetcherImpl.FetcherImpl;
import syndexmx.github.com.tgsiren.domain.NewsFeedMessage;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static syndexmx.github.com.tgsiren.utils.HtmlBlockCutter.cutTagedBlockOut;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractAllTaggedBlocks;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractTagedBlock;

@Service
@Slf4j
public class WebMonitorImpl implements WebMonitor {

    private Fetcher fetcher;
    private Integer updateInterval;
    private volatile boolean initiated = false;
    private WebSourcesConfig webSourcesConfig;
    private MessageFilterConfig messageFilterConfig;
    private ChannelService channelService;

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                   @Autowired WebSourcesConfig webSourcesConfig,
                   @Autowired MessageFilterConfig messageFilterConfig,
                    @Autowired ChannelService channelService) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.webSourcesConfig = webSourcesConfig;
        this.messageFilterConfig = messageFilterConfig;
        this.channelService = channelService;
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
        List<String> urlChannelSet = channelService.listAllChannels().stream()
                .map(channelDto -> {
                    return channelDto.getUrl();
                }).toList();
        for (String url : urlChannelSet)
        {
            String receivedNewsFeed =
                    cutTagedBlockOut(cutTagedBlockOut(cutTagedBlockOut(
                            extractTagedBlock(
                                    fetcher.getPage(url).toString(),
                                    "main",
                                    "tgme_main"),
                            "picture"), "svg"), "tg-emoji");
            scanNewsFeed(url, receivedNewsFeed);
        }
    }

    private void scanNewsFeed(String url, String receivedNewsFeed) {
        log.info("Scanning " + url);
        List<String> filterList = messageFilterConfig.getFilerItems();
        List<String> blocks =
                extractAllTaggedBlocks(receivedNewsFeed, "div", "tgme_widget_message_bubble");
        List<String> filteredBlocks = blocks.stream()
                .filter(block -> {
                    for (String string : filterList) {
                        if (block.indexOf(string) >= 0) return true;
                    }
                    return true; // Temporarily Disabled
                })
                .toList();
        System.out.println(filteredBlocks.toString());
    };

    private void goToSleep() {
        try {
            Thread.sleep(updateInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
