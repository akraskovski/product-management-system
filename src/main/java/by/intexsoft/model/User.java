package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
@Table
public class User extends BaseEntity {

    /**
     * User name
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * User password
     */
    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}