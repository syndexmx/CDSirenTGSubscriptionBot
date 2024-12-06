package syndexmx.github.com.tgsiren.controllers.webfetcher.fetcherImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.webfetcher.Fetcher;

import java.io.IOException;
import java.net.URL;

@Service
public class FetcherImpl implements Fetcher {

    public Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }
}
