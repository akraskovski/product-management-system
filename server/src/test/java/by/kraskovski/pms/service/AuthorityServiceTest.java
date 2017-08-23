package by.kraskovski.pms.service;

import by.kraskovski.pms.domain.Authority;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import by.kraskovski.pms.repository.AuthorityRepository;
import by.kraskovski.pms.service.impl.AuthorityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceTest {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    @Test
    public void findByIdTest() {
        final Authority authority = new Authority(AuthorityEnum.ROLE_ADMIN);
        when(authorityRepository.findOne(anyInt())).thenReturn(authority);

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
