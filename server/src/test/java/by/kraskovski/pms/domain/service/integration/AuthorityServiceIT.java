package by.kraskovski.pms.domain.service.integration;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.service.AuthorityService;
import by.kraskovski.pms.domain.service.integration.config.ServiceTestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jsonwebtoken.lang.Assert.notNull;

/**
 * Integration test for {@link AuthorityService}.
 */
public class AuthorityServiceIT extends ServiceTestConfig {

    @Autowired
    private AuthorityService authorityService;

    @Test
    public void findByNameIfExistsTest() {
        authorityService.create(new Authority(AuthorityEnum.ROLE_ADMIN));
        final Authority authority = authorityService.findByName(AuthorityEnum.ROLE_ADMIN);
        notNull(authority);
    }
}
