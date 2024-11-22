package syndexmx.github.com.tgsiren.webmonitor;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface Fetcher {

    public Document getPage(String url) throws IOException;
}
