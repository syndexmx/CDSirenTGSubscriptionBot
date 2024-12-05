package syndexmx.github.com.tgsiren.services.channelservices.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.dto.ChannelDto;
import syndexmx.github.com.tgsiren.dtomappers.ChannelMapper;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.repositories.ChannelRepository;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

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
