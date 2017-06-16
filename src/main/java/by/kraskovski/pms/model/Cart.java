package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinTable;
import java.util.List;

@Entity
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(targetEntity = Product.class)
    @JoinTable(
            name = "cart_product",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> productList;

    private double totalCost;

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setProductList(final List<Product> productList) {
        this.productList = productList;
    }

    public void setTotalCost(final double totalCost) {
        this.totalCost = totalCost;
    }
}
