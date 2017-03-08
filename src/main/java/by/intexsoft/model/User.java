package by.intexsoft.model;

import by.intexsoft.model.enums.Authority;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    @Column
    public String username;

    /**
     * User password
     */
    @Column
    public String password;

    /**
     * ManyToMany relation to {@link Role} entities
     */
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

    /**
     * Getter method returns {@link User}'s collection of role names
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> currentRoles = new ArrayList<>();
        for (Role role : roles)
            currentRoles.add(role.name);
        return currentRoles;
    }
}