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

    private final SubscriberRepository subscriberRepository;
    private final FeedMessageService feedMessageService;
    private final ChannelService channelService;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, FeedMessageService feedMessageService, ChannelService channelService) {
        this.subscriberRepository = subscriberRepository;
        this.feedMessageService = feedMessageService;
        this.channelService = channelService;
    }

    @Override
    public String serve(Long subscriberId, String command) {
        Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
        if (optionalSubscriber.isEmpty()) {
            Subscriber newSubscriber = Subscriber.builder()
                    .id(subscriberId)
                    .build();
            Subscriber subscriber = subscriberRepository.save(newSubscriber);
            optionalSubscriber = Optional.of(subscriber);
        }
        return parseCommand(optionalSubscriber.get().getId(), command);
    }


    private String parseCommand(Long subscriber, String command) {
        //TODO
        //tgBotController.sendMessage(command, subscriber);
        if (command.equals("Подписки")) {
            StringBuffer subs = new StringBuffer();
            List<ChannelDto> list = channelService.listAllChannels();
            list.forEach(item -> subs.append(item.getName() + "\n"));
            return subs.toString();
        }
        if (command.equals("Последнее")) {
            Optional<FeedMessage> lastFeedMessageOptional = feedMessageService.getLast();
            if (lastFeedMessageOptional.isPresent()) {
                FeedMessage lastMessage = lastFeedMessageOptional.get();
                String message = lastMessage.getText();
                return message;
            } else {
                return "Нет сообщений";
            }

        }
        return "Команда неизвестна";
    }

}
