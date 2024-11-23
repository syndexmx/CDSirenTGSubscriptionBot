package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.config.WebSourcesConfig;
import syndexmx.github.com.tgsiren.controllers.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.controllers.webmonitor.fetcherImpl.FetcherImpl;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;

import java.io.IOException;
import java.util.Map;

import static syndexmx.github.com.tgsiren.utils.HtmlBlockCutter.cutTagedBlockOut;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractTagedBlock;

@Service
@Slf4j
public class WebMonitorImpl implements WebMonitor {

    private Fetcher fetcher;
    private Integer updateInterval;
    private volatile boolean initiated = false;
    private WebSourcesConfig webSourcesConfig;

    WebMonitorImpl(@Autowired Fetcher fetcher,
                   @Value("${web-monitor.update-interval.ms}") Integer intervalValue,
                   @Autowired WebSourcesConfig webSourcesConfig) {
        this.fetcher = fetcher;
        this.updateInterval = intervalValue;
        this.webSourcesConfig = webSourcesConfig;
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
        }
    }


    private void goToSleep() {
        try {
            Thread.sleep(updateInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
