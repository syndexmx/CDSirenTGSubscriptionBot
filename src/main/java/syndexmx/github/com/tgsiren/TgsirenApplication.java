package syndexmx.github.com.tgsiren;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import syndexmx.github.com.tgsiren.controllers.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.controllers.webmonitor.fetcherImpl.FetcherImpl;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl.WebMonitorImpl;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockCutter.cutTagedBlockOut;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractTagedBlock;

@SpringBootApplication
public class TgsirenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgsirenApplication.class, args);
	}

}
