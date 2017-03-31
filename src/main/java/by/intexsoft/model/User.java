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
    public String username;

    /**
     * User password
     */
    @Column(nullable = false)
    public String password;

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    public List<Authority> authorities;

}