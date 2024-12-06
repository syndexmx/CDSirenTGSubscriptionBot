package syndexmx.github.com.tgsiren.services.susbcribers;

import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.FeedMessage;

@Service
public interface SubscriberService {

    void serve(Long subscriberId, String command);

    void notifyAllInterested(String url, FeedMessage feedMessage);
}
