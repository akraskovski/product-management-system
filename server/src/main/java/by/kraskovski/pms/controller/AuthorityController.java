package by.kraskovski.pms.controller;

import by.kraskovski.pms.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for {@link AuthorityService}.
 */
@RestController
@RequestMapping("/authority")
@Slf4j
public class AuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(final AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * Return json-information about all authorities in database.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity loadAuthorities() {
        log.info("Start loading all authorities");
        return ResponseEntity.ok(authorityService.findAll());
    }
}
