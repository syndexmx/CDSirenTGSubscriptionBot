package syndexmx.github.com.tgsiren.services.filterservices;

import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.dto.FilterDto;
import syndexmx.github.com.tgsiren.entities.Filter;

import java.util.List;
import java.util.Optional;

@Service
public interface FilterService {

    Optional<Filter> addFilter(Filter filter);
    List<FilterDto> listAllFilters();
}
