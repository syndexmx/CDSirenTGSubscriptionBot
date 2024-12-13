package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.NotificationService;
import syndexmx.github.com.tgsiren.services.susbcribers.SubscriberService;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private SubscriberService subscriberService;

    NotificationServiceImpl(@Autowired SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @Override
    public void notifyAllInterested(String url, FeedMessage feedMessage) {
        // TODO
        subscriberService.notifyAllInterested(url, feedMessage);
        log.info("New message \n\n" + feedMessage.getFooter().toString() + "\n\n");
    }
}
