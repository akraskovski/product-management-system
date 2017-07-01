package by.kraskovski.pms.repository;

import by.kraskovski.pms.Application;
import by.kraskovski.pms.model.Authority;
import by.kraskovski.pms.model.enums.AuthorityEnum;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@TestPropertySource("classpath:/application-test.properties")
public class AuthorityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    @After
    public void after() {
        authorityRepository.deleteAll();
    }

    @Test
    public void findByAuthorityTest() {
        entityManager.persist(new Authority(AuthorityEnum.ROLE_USER));
        final Authority authority = authorityRepository.findByAuthority(AuthorityEnum.ROLE_USER);
        assertNotNull(authority.getId());
        assertEquals(authority.getAuthority(), AuthorityEnum.ROLE_USER.name());
    }
}