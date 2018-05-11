package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.application.controller.dto.AuthorityDto;
import by.kraskovski.pms.domain.service.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for {@link AuthorityService}.
 */
@RestController
@RequestMapping("/authority")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorityController {

    private final AuthorityService authorityService;
    private final Mapper mapper;

    /**
     * Return json-information about all authority in database.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorityDto> loadAuthorities() {
        log.info("Start loading all authority");
        return authorityService.findAll().stream()
                .map(authority -> mapper.map(authority, AuthorityDto.class))
                .collect(toList());
    }
}
