package by.kraskovski.pms.repository;

import by.kraskovski.Application;
import by.kraskovski.pms.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameTest() {
        String username = "admin";
        User user = userRepository.findByUsername(username);
        assertNotNull(user);
    }
}