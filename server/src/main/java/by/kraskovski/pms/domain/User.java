package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.Authentication;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity implements Authentication {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Cart cart;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Transient
    private boolean authenticated;

    private String firstName;

    private String lastName;

    private LocalDateTime createDate;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String avatar;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final String id,  final String username, final String password) {
        this.setId(id);
        this.username = username;
        this.password = password;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return username;
    }

    @Override
    @JsonIgnore
    public Object getCredentials() {
        return password;
    }

    @Override
    @JsonIgnore
    public Object getDetails() {
        return this;
    }

    @Override
    @JsonIgnore
    public Object getPrincipal() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @JsonIgnore
    public Cart getCart() {
        return this.cart;
    }

    public void addCart(final Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

    public void removeCart() {
        if (cart != null) {
            cart.setUser(null);
        }
        this.cart = null;
    }
}
