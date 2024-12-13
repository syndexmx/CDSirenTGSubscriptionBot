package syndexmx.github.com.tgsiren.services.filterservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syndexmx.github.com.tgsiren.dto.FilterDto;
import syndexmx.github.com.tgsiren.dto.dtomappers.FilterMapper;
import syndexmx.github.com.tgsiren.entities.Filter;
import syndexmx.github.com.tgsiren.repositories.FilterRepository;
import syndexmx.github.com.tgsiren.services.filterservices.FilterService;

import java.util.List;
import java.util.Optional;

@Service
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;

    FilterServiceImpl(@Autowired FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }


    @Override
    public Optional<Filter> addFilter(Filter inFilter) {
        Optional<Filter> existingfilter = filterRepository.findFilter(inFilter.getFilterString());
        if (existingfilter.isPresent()) {
            return existingfilter;
        } else {
            Filter savedFilter = filterRepository.save(inFilter);
            return Optional.ofNullable(savedFilter);
        }
    }

    @Override
    public List<FilterDto> listAllFilters() {
        return filterRepository.findAll().stream()
                .map(filter -> {
                    FilterMapper filterMapper = new FilterMapper();
                    FilterDto dto = filterMapper.filterToFilterDto(filter);
                    return dto;
                })
                .toList();
    }
}
