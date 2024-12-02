package syndexmx.github.com.tgsiren.controllers.admincontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syndexmx.github.com.tgsiren.dto.ChannelDto;
import syndexmx.github.com.tgsiren.dtomappers.ChannelMapper;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.services.channelservices.ChannelService;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping
public class ChannelController {

    private final ChannelService channelService;

    ChannelController(@Autowired ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping(path = "/api/v0/channels")
    ResponseEntity<String> addChannel(@RequestBody ChannelDto channelDto) {
        ChannelMapper channelMapper = new ChannelMapper();
        Channel channel = channelMapper.channelDtoToChannel(channelDto);
        Optional<Channel> savedChannelOptional = channelService.addChannel(channel);
        if (savedChannelOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        ChannelDto createdDto = channelMapper.channelToChannelDto(
                savedChannelOptional.get());
        ResponseEntity response = new ResponseEntity(createdDto, HttpStatus.CREATED);
        return response;
    }
}
