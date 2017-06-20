package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ProductStock extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToMany(
            mappedBy = "productStock",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<CartProductStock> cartProductStocks;

    private int productsCount;

    public Product getProduct() {
        return product;
    }

    public Stock getStock() {
        return stock;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public void setStock(final Stock stock) {
        this.stock = stock;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }
}
