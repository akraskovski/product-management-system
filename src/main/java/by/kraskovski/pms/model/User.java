package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

/**
 * Description entity by database table "user"
 */
@Entity
public class User extends BaseEntity implements Authentication {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Transient
    private boolean authenticated = true;

    private String firstName;

    private String lastName;

    public User() {
        super();
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final int id,  final String username, final String password) {
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Cart getCart() {
        return cart;
    }

    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
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
