package by.intexsoft.controller;
import by.intexsoft.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles requests for the {@link UserService}
 */
@RestController
@RequestMapping("/service")
public class SimpleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    /**
     * Simple method to print string
     * @return test string
     */
    @RequestMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start hello");
        return "Hello, my name is Artem";
    }

}
