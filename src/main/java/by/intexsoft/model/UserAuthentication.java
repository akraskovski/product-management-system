package by.intexsoft.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Model representation for security
 */
public class UserAuthentication implements Authentication {

    private final User user;
    private boolean authenticated = true;

    public UserAuthentication(User user) {
        this.user = user;
    }

    /**
     * Get user authorities
     * Works with security
     *
     * @return authoroties
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.authorities;
    }

    /**
     * Get user password
     *
     * @return password
     */
    @Override
    public Object getCredentials() {
        return user.password;
    }

    /**
     * Get detail information about user
     *
     * @return user
     */
    @Override
    public Object getDetails() {
        return user;
    }

    /**
     * Get username
     *
     * @return username
     */
    @Override
    public Object getPrincipal() {
        return user.username;
    }

    /**
     * Authentication status
     *
     * @return authenticated
     */
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Setter for user's authentication
     *
     * @param authenticated
     * @throws IllegalArgumentException
     */
    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    /**
     * Get username
     *
     * @return username
     */
    @Override
    public String getName() {
        return user.username;
    }

}
