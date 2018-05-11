package by.kraskovski.pms.application.security.model;

import by.kraskovski.pms.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

/**
 * The type Jwt authentication factory.
 */
public final class JwtAuthenticationFactory {

    private JwtAuthenticationFactory() {
    }

    /**
     * Create jwt authentication.
     *
     * @param user the user
     * @return the jwt authentication
     */
    public static JwtAuthentication create(final User user) {
        final String authorityName = user.getAuthority().getName().name();
        final List<GrantedAuthority> simpleGrantedAuthorities
                = Collections.singletonList(new SimpleGrantedAuthority(authorityName));

        return new JwtAuthentication(user, simpleGrantedAuthorities);
    }
}
