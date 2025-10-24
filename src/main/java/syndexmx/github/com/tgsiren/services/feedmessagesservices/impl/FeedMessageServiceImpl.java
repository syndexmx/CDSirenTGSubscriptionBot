package syndexmx.github.com.tgsiren.services.feedmessagesservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.entities.FeedMessage;
import syndexmx.github.com.tgsiren.repositories.ChannelRepository;
import syndexmx.github.com.tgsiren.repositories.FeedMessageRepository;
import syndexmx.github.com.tgsiren.services.feedmessagesservices.FeedMessageService;

import java.util.Optional;

@Service
public class FeedMessageServiceImpl implements FeedMessageService {

    private final FeedMessageRepository feedMessageRepository;

    FeedMessageServiceImpl(@Autowired FeedMessageRepository feedMessageRepository) {
        this.feedMessageRepository = feedMessageRepository;
    }

    @Override
    public Optional<FeedMessage> addMessage(FeedMessage feedMessage) {
        Optional<FeedMessage> existingMessage = feedMessageRepository.findMessageByFooter(feedMessage.getFooter());
        if (existingMessage.isPresent()) {
            return Optional.empty();
        } else {
            FeedMessage savedMessage = feedMessageRepository.save(feedMessage);
            return Optional.ofNullable(savedMessage);
        }
    }

    @Override
    public Optional<FeedMessage> getLast() {
        return feedMessageRepository.findTopByOrderByCreatedAtDesc();
    }
}
