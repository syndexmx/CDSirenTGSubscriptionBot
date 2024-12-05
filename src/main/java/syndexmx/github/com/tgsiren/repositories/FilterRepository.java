package syndexmx.github.com.tgsiren.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import syndexmx.github.com.tgsiren.entities.Channel;
import syndexmx.github.com.tgsiren.entities.Filter;

import java.util.Optional;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {

    @Query(
            value = "SELECT * FROM filters f WHERE f.filter_string = ?1",
            nativeQuery = true)
    Optional<Filter> findFilter(String filterString);

}
