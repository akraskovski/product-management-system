package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
            orphanRemoval = true)
    private Set<CartProductStock> cartProductStocks;

    public Cart() {
        this.createDate = LocalDateTime.now();
    }

    private LocalDateTime createDate;

    private double totalCost;

    public Set<CartProductStock> getCartProductStocks() {
        return cartProductStocks;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
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

    public void setCreateDate(final LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
