package syndexmx.github.com.tgsiren.dtomappers;

import syndexmx.github.com.tgsiren.dto.FilterDto;
import syndexmx.github.com.tgsiren.entities.Filter;

public class FilterMapper {

    public Filter filterDtoToFilter(FilterDto filterDto) {
        return Filter.builder()
                .filterString(filterDto.getFilterString())
                .build();
    }

    public FilterDto filterToFilterDto(Filter filter) {
        return FilterDto.builder()
                .id(filter.getId())
                .filterString(filter.getFilterString())
                .build();
    }

}
