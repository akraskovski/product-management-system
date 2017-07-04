package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ProductStock extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToMany(
            mappedBy = "productStock",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<CartProductStock> cartProductStocks;

    private int productsCount;

    public ProductStock(final Product product, final Stock stock, final int productsCount) {
        this.product = product;
        this.stock = stock;
        this.productsCount = productsCount;
    }

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

    public void setProductsCount(final int productsCount) {
        this.productsCount = productsCount;
    }
}
