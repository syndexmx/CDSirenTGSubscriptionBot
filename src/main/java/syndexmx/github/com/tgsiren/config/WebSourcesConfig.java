package syndexmx.github.com.tgsiren.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Getter
@Configuration
@PropertySource("websources.properties")
public class WebSourcesConfig {

    final private Map<String, String> webSourcesMap;

    public WebSourcesConfig(@Value("#{${web-sources.urls}}") Map<String, String> webSourcesMap) {
        this.webSourcesMap = webSourcesMap;
    }
}
