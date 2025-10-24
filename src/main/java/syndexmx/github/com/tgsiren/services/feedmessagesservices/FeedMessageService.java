package syndexmx.github.com.tgsiren.services.feedmessagesservices;

import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.entities.FeedMessage;

import java.util.Optional;

@Service
public interface FeedMessageService {

    Optional<FeedMessage> addMessage(FeedMessage inMessage);
    Optional<FeedMessage> getLast();
}
