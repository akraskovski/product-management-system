package by.kraskovski.model;

import by.kraskovski.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import java.util.Collection;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
public class User extends BaseEntity implements Authentication {

    @Transient
    private boolean authenticated = true;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.setId(id);
        this.username = username;
        this.password = password;
    }

    /**
     * User name
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * User password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get user authorities
     * Works with security
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final List<Authority> authorities) {
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
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
}
