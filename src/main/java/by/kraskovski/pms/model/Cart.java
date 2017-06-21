package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Set;

@Entity
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<CartProductStock> cartProductStocks;

    private double totalCost = 0.0;

    public User getUser() {
        return user;
    }

    public Set<CartProductStock> getCartProductStocks() {
        return cartProductStocks;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setTotalCost(final double totalCost) {
        this.totalCost = totalCost;
    }
}
