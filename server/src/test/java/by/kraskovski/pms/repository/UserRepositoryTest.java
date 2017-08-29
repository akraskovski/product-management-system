package by.kraskovski.pms.repository;

import by.kraskovski.pms.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration test for {@link UserRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        final User user = entityManager.persist(prepareUser());
        final User foundUser = userRepository.findByUsername(user.getUsername());
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }
}