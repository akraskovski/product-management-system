package by.kraskovski.pms.application.security.model;

import by.kraskovski.pms.domain.model.Authority;
import by.kraskovski.pms.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtAuthenticationFactory {

    private JwtAuthenticationFactory() {
    }

    public static JwtAuthentication create(final User user) {
        return new JwtAuthentication(user, mapToGrantedAuthorities(user.getAuthorities()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
