package syndexmx.github.com.tgsiren.services.susbcribers;

import org.springframework.stereotype.Service;

@Service
public interface SubscriberService {

    void serve(Long subscriberId, String command);
}
