package syndexmx.github.com.tgsiren.services.susbcribers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.controllers.tgbot.TgBotController;
import syndexmx.github.com.tgsiren.dto.ChannelDto;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.entities.Subscriber;
import syndexmx.github.com.tgsiren.repositories.SubscriberRepository;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;
import syndexmx.github.com.tgsiren.services.feedmessagesservices.FeedMessageService;
import syndexmx.github.com.tgsiren.services.susbcribers.SubscriberService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    FeedMessageService feedMessageService;

    @Autowired
    ChannelService channelService;

    @Autowired
            @Lazy
    TgBotController tgBotController;



    @Override
    public void serve(Long subscriberId, String command) {
        Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
        if (optionalSubscriber.isEmpty()) {
            Subscriber newSubscriber = Subscriber.builder()
                    .id(subscriberId)
                    .build();
            Subscriber subscriber = subscriberRepository.save(newSubscriber);
            optionalSubscriber = Optional.of(subscriber);
        }
        parseCommand(optionalSubscriber.get().getId(), command);
    }

    @Override
    public void notifyAllInterested(String url, FeedMessage feedMessage) {
        String message = feedMessage.getText();
        List<Subscriber> subscribersList = subscriberRepository.findAll();
        for (Subscriber subscriber : subscribersList) {
            Long id = subscriber.getId();
            tgBotController.sendMessage(message, id);
        }
    }

    private void parseCommand(Long subscriber, String command) {
        //TODO
        //tgBotController.sendMessage(command, subscriber);
        if (command.equals("Подписки")) {
            StringBuffer subs = new StringBuffer();
            List<ChannelDto> list = channelService.listAllChannels();
            list.forEach(item -> subs.append(item.getName() + "\n"));
            tgBotController.sendMessage(subs.toString(), subscriber);
        }
        if (command.equals("Последнее")) {
            Optional<FeedMessage> lastFeedMessageOptional = feedMessageService.getLast();
            if (lastFeedMessageOptional.isPresent()) {
                FeedMessage lastMessage = lastFeedMessageOptional.get();
                String message = lastMessage.getText();
                tgBotController.sendMessage( message, subscriber);
            } else {
                tgBotController.sendMessage("Нет сообщений", subscriber);
            }

        }
    }

}
