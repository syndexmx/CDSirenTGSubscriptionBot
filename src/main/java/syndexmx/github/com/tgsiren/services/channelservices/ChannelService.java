package syndexmx.github.com.tgsiren.services.channelservices;

import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.entities.Channel;

import java.util.Optional;

@Service
public interface ChannelService {

    Optional<Channel> addChannel(Channel inChannel);

}
