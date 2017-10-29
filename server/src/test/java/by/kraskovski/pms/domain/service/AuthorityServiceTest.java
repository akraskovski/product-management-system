package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.enums.AuthorityEnum;
import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private UserAuthorityService authorityService;

    @Test
    public void findByIdTest() {
        final Authority authority = new Authority(AuthorityEnum.ROLE_ADMIN);
        when(authorityRepository.findOne(anyString())).thenReturn(authority);

        assertEquals(authority, authorityService.find(authority.getId()));
        verify(authorityRepository).findOne(authority.getId());
    }

    @Test
    public void findByNameTest() {
        final Authority authority = new Authority(AuthorityEnum.ROLE_ADMIN);
        when(authorityRepository.findByAuthority(anyObject())).thenReturn(authority);

        assertEquals(authority, authorityService.findByName(AuthorityEnum.ROLE_ADMIN));
        verify(authorityRepository).findByAuthority(AuthorityEnum.ROLE_ADMIN);
    }
}
