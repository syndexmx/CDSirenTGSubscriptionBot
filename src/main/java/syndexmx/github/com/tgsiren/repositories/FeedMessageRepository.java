package syndexmx.github.com.tgsiren.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.entities.FeedMessage;

import java.util.Optional;

@Repository
public interface FeedMessageRepository extends JpaRepository<FeedMessage, Long> {

    @Query(
            value = "SELECT * FROM feed_messages fm WHERE fm.footer = ?1",
            nativeQuery = true)
    Optional<FeedMessage> findMessageByFooter(String footer);
}
