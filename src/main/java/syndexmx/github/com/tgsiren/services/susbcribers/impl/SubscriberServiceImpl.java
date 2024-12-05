package syndexmx.github.com.tgsiren.services.susbcribers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.Subscriber;
import syndexmx.github.com.tgsiren.repositories.SubscriberRepository;
import syndexmx.github.com.tgsiren.services.susbcribers.SubscriberService;

import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(@Autowired SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public void serve(Long subscriberId, String command) {
        Optional<Subscriber> optionalSubscriber = subscriberRepository.findById(subscriberId);
        if (optionalSubscriber.isEmpty()) {
            Subscriber newSubscriber = new Subscriber(subscriberId);
            Subscriber subscriber = subscriberRepository.save(newSubscriber);
            optionalSubscriber = Optional.of(subscriber);
        }
        parseCommand(optionalSubscriber.get(), command);
    }

    private void parseCommand(Subscriber subscriber, String command) {
        if (command.indexOf("/") == 0) {
            parseAdminCommand(subscriber, command);
        } else {
            parseSubscriberCommand(subscriber, command);
        }
    }

    private void parseAdminCommand(Subscriber subscriber, String command) {

    }

    private void parseSubscriberCommand(Subscriber subscriber, String command) {

    }
}
