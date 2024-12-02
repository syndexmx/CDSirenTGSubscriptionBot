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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                   @Autowired WebSourcesConfig webSourcesConfig,
                   @Autowired MessageFilterConfig messageFilterConfig) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.webSourcesConfig = webSourcesConfig;
        this.messageFilterConfig = messageFilterConfig;
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
        Map<String, String> webSourcesMap = webSourcesConfig.getWebSourcesMap();
        for (String sourceName : webSourcesMap.keySet())
        {
            String url = webSourcesMap.get(sourceName);
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
        List<String> filterList = messageFilterConfig.getFilerItems();
        List<String> blocks =
                extractAllTaggedBlocks(receivedNewsFeed, "div", "tgme_widget_message_bubble");
        List<String> filteredBlocks = blocks.stream()
                .filter(block -> {
                    for (String string : filterList) {
                        if (block.indexOf(string) >= 0) return true;
                    }
                    return false;
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
