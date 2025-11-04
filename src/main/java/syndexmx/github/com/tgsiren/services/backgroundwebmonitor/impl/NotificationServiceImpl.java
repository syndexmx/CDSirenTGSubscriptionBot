package syndexmx.github.com.tgsiren.services.backgroundwebmonitor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.tgbot.TgBotController;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.entities.Subscriber;
import syndexmx.github.com.tgsiren.repositories.SubscriberRepository;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.NotificationService;
import syndexmx.github.com.tgsiren.services.susbcribers.SubscriberService;
import syndexmx.github.com.tgsiren.utils.Crayon;

import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final SubscriberRepository subscriberRepository;
    private final TgBotController tgBotController;

    NotificationServiceImpl(@Autowired SubscriberService subscriberService, SubscriberRepository subscriberRepository, TgBotController tgBotController) {
        this.subscriberRepository = subscriberRepository;
        this.tgBotController = tgBotController;
    }

    @Override
    public void notifyAllInterested(String url, FeedMessage feedMessage) {
        // TODO
        doNotifyAllInterested(url, feedMessage);
        log.info(Crayon.magenta("Message") + ": " + Crayon.purple(feedMessage.getFooter().toString()) +
                "\n" + Crayon.cyan(feedMessage.getText().replaceAll("\\s+", " ")));
    }

    private void doNotifyAllInterested(String url, FeedMessage feedMessage) {
        String message = feedMessage.getText();
        List<Subscriber> subscribersList = subscriberRepository.findAll();
        for (Subscriber subscriber : subscribersList) {
            Long id = subscriber.getId();
            tgBotController.sendMessage(message, id);
        }
    }

}