package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static by.kraskovski.pms.utils.TestUtils.prepareUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityService authorityService;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    public void createTest() {
        final User user = prepareUser();
        when(authorityService.findByName(AuthorityEnum.ROLE_USER)).thenReturn(new Authority(AuthorityEnum.ROLE_USER));
        when(userRepository.save(user)).thenReturn(user);
        when(encoder.encode(anyString())).thenReturn(RandomStringUtils.randomAlphabetic(5));

        userService.create(user);

        verify(authorityService).findByName(AuthorityEnum.ROLE_USER);
        verify(userRepository).save(eq(user));
    }

    @Test
    public void findByIdTest() {
        final User user = prepareUser();
        when(userRepository.findOne(eq(user.getId()))).thenReturn(user);

        assertEquals(user, userService.find(user.getId()));
        verify(userRepository).findOne(eq(user.getId()));
    }

    @Test
    public void findByUsernameTest() {
        final User user = prepareUser();
        when(userRepository.findByUsername(eq(user.getUsername()))).thenReturn(user);

        assertEquals(user, userService.findByUsername(user.getUsername()));
        verify(userRepository).findByUsername(user.getUsername());
    }
}