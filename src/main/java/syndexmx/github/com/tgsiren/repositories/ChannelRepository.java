package syndexmx.github.com.tgsiren.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import syndexmx.github.com.tgsiren.entities.Channel;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query(
            value = "SELECT * FROM channels ch WHERE ch.channel_url = ?1",
            nativeQuery = true)
    Optional<Channel> findChannelByUrl(String url);
}
