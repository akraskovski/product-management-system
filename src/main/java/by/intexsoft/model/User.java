package by.intexsoft.model;

import by.intexsoft.model.enums.Authority;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
@Table
public class User extends AbstractPersistable<Integer>{

    /**
     * User name field
     */
    @Column
    public String username;

    /**
     * User password field
     */
    @Column
    public String password;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            foreignKey = @ForeignKey(name = "fk_user_role_user1"),
            inverseJoinColumns = {@JoinColumn(name = "role_id")},
            inverseForeignKey = @ForeignKey(name = "fk_user_role_role1"),
            uniqueConstraints = @UniqueConstraint(name = "user_role_pkey",
                    columnNames = {"user_id", "role_id"}))
    private List<Role> roles;

    /**
     * User collection of roles
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> currentRoles = new ArrayList<>();
        for (Role role : roles)
            currentRoles.add(role.name);
        return currentRoles;
    }
}