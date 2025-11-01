package syndexmx.github.com.tgsiren.services.channelservices.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.dto.ChannelDto;
import syndexmx.github.com.tgsiren.dto.dtomappers.ChannelMapper;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.repositories.ChannelRepository;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;
import syndexmx.github.com.tgsiren.utils.Crayon;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    @Lazy
    @Autowired
    private WebMonitor webMonitor;

    ChannelServiceImpl(@Autowired ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Optional<Channel> addChannel(Channel inChannel) {
        Optional<Channel> existingChannel = channelRepository.findChannelByUrl(inChannel.getUrl());
        if (existingChannel.isPresent()) {
            return existingChannel;
        } else {
            Channel savedChannel = channelRepository.save(inChannel);
            log.info(Crayon.white("Channel is added") + ": " + Crayon.cyan(savedChannel.getName()) + " " + Crayon.purple(savedChannel.getUrl()));
            try {
                Thread thread = new Thread(() -> { webMonitor.startMonitor(); });
                thread.start();
            } catch (RuntimeException exception) {
                log.error("Error occured while starting Monitor service");
                exception.printStackTrace();
            }

            return Optional.ofNullable(savedChannel);
        }
    }

    @Override
    public List<ChannelDto> listAllChannels() {
        return channelRepository.findAll().stream()
                .map(channel -> {ChannelMapper channelMapper = new ChannelMapper();
                    ChannelDto dto = channelMapper.channelToChannelDto(channel);
                    return dto;
                })
                .toList();
    }
}