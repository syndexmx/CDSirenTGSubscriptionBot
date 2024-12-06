package syndexmx.github.com.tgsiren.services.backgroundwebmonitor;

import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.FeedMessage;

@Service
public interface NotificationService {

    public void notifyAllInterested(String url, FeedMessage feedMessage);
}
