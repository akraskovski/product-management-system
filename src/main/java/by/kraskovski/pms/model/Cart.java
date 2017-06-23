package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval= true)
    private Set<CartProductStock> cartProductStocks;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    private LocalDateTime createdDate;

    private double totalCost;

    public Set<CartProductStock> getCartProductStocks() {
        return cartProductStocks;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
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

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
