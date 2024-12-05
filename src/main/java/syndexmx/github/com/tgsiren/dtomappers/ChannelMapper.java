package syndexmx.github.com.tgsiren.dtomappers;

import syndexmx.github.com.tgsiren.dto.ChannelDto;
import syndexmx.github.com.tgsiren.entities.Channel;

public class ChannelMapper {

    public Channel channelDtoToChannel(ChannelDto channelDto) {
        return Channel.builder()
                //.id(channelDto.getId())
                .name(channelDto.getName())
                .url(channelDto.getUrl())
                .build();
    }

    public ChannelDto channelToChannelDto(Channel channel) {
        return ChannelDto.builder()
                .id(channel.getId())
                .name(channel.getName())
                .url(channel.getUrl())
                .build();
    }
}
