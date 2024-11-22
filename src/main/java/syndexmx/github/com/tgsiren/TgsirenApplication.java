package syndexmx.github.com.tgsiren;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.webmonitor.Fetcher;
import syndexmx.github.com.tgsiren.webmonitor.fetcherImpl.FetcherImpl;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockCutter.cutTagedBlockOut;
import static syndexmx.github.com.tgsiren.utils.HtmlBlockExtractor.extractTagedBlock;

@SpringBootApplication
@Slf4j
public class TgsirenApplication {

    static Fetcher fetcher = new FetcherImpl();

	public static void main(String[] args) {
		SpringApplication.run(TgsirenApplication.class, args);


		for (int i = 0; i < 1; i++) {
            try {
                System.out.println(
                        cutTagedBlockOut(cutTagedBlockOut(cutTagedBlockOut(
                            extractTagedBlock(
                            fetcher.getPage("https://t.me/s/mchs_bryansk").toString(),
                                    "main",
                                    "tgme_main"),
                                "picture"), "svg"), "tg-emoji"
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}

}
