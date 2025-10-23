package syndexmx.github.com.tgsiren.controllers.admincontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syndexmx.github.com.tgsiren.dto.FilterDto;
import syndexmx.github.com.tgsiren.dto.dtomappers.FilterMapper;
import syndexmx.github.com.tgsiren.entities.Filter;
import syndexmx.github.com.tgsiren.services.filterservices.FilterService;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping
public class FilterController {

    private final FilterService filterService;

    FilterController(@Autowired FilterService filterService) {
        this.filterService = filterService;
    }

    @Value("${admin-token}")
    private String adminToken;

    @PostMapping(path = "/api/v0/filters")
    ResponseEntity<FilterDto> addFiler(@RequestBody FilterDto filterDto, @RequestParam String token) {
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (!token.equals(adminToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        FilterMapper filterMapper = new FilterMapper();
        Filter filter = filterMapper.filterDtoToFilter(filterDto);
        Optional<Filter> savedFilterOptional = filterService.addFilter(filter);
        if (savedFilterOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        FilterDto createdDto = filterMapper.filterToFilterDto(
                savedFilterOptional.get());
        ResponseEntity response = new ResponseEntity(createdDto, HttpStatus.CREATED);
        return response;

    }

    @GetMapping(path = "/api/v0/filters")
    ResponseEntity<FilterDto> listAllFilters() {
        return new ResponseEntity(filterService.listAllFilters(), HttpStatus.OK);
    }

}
