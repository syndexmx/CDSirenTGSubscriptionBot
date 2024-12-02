package syndexmx.github.com.tgsiren.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syndexmx.github.com.tgsiren.entities.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
