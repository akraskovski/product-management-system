package by.kraskovski.pms.application.controller;

import by.kraskovski.pms.domain.service.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link AuthorityService}.
 */
@RestController
@RequestMapping("/authority")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorityController {

    private final AuthorityService authorityService;

    /**
     * Return json-information about all authorities in database.
     */
    @GetMapping
    public ResponseEntity loadAuthorities() {
        log.info("Start loading all authorities");
        return ResponseEntity.ok(authorityService.findAll());
    }
}
