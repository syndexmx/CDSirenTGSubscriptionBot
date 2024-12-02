package syndexmx.github.com.tgsiren.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Getter
@Configuration
@PropertySource("filter.properties")
public class MessageFilterConfig {

    final private List<String> filerItems;

    public MessageFilterConfig(@Value("${search-for.words}") List<String> filerItems) {
        this.filerItems = filerItems;
    }
}
