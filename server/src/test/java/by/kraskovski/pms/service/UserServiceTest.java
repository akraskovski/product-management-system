package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.repository.UserRepository;
import by.kraskovski.pms.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityService authorityService;

    @InjectMocks
    private UserServiceImpl userService;

    private User prepareUser() {
        final String id = random(40);
        final String username = random(20);
        final String password = random(20);
        return new User(id, username, password);
    }

    @Test
    public void createTest() {
        final User user = prepareUser();
        when(authorityService.findByName(AuthorityEnum.ROLE_USER)).thenReturn(new Authority(AuthorityEnum.ROLE_USER));
        when(userRepository.save((User) anyObject())).thenReturn(user);

        assertNotEquals(userService.create(new User()).getId(), 0);
        verify(authorityService).findByName(anyObject());
        verify(userRepository).save((User) anyObject());
    }

    @Test
    public void findByIdTest() {
        final User user = prepareUser();
        when(userRepository.findOne(anyString())).thenReturn(user);

        assertEquals(user, userService.find(user.getId()));
        verify(userRepository).findOne(anyString());
    }

    @Test
    public void findByUsernameTest() {
        final User user = prepareUser();
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        assertEquals(user, userService.findByUsername(user.getUsername()));
        verify(userRepository).findByUsername(anyString());
    }
}