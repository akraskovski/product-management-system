package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<CartProductStock> cartProductStocks;

    private double totalCost;

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
