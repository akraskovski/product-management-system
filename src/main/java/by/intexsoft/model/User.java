package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
@Table
public class User extends BaseEntity implements Authentication{

    @Transient
    private boolean authenticated = true;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities;

    /**
     * User name
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * User password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get user authorities
     * Works with security
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Get username
     */
    @Override
    @JsonIgnore
    public String getName() {
        return username;
    }

    /**
     * Get user credentials
     */
    @Override
    @JsonIgnore
    public Object getCredentials() {
        return password;
    }

    /**
     * Get detail information about user
     */
    @Override
    @JsonIgnore
    public Object getDetails() {
        return this;
    }

    /**
     * Get user principal
     */
    @Override
    @JsonIgnore
    public Object getPrincipal() {
        return username;
    }

    /**
     * User authentication status
     */
    @Override
    @JsonIgnore
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Set for authentication
     */
    @Override
    @JsonIgnore
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
}