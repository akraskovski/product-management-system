package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cart_product_stock")
public class CartProductStock extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_stock_id")
    private ProductStock productStock;

    private int productCount;

    public void setCart(final Cart cart) {
        this.cart = cart;
    }

    public ProductStock getProductStock() {
        return productStock;
    }

    public void setProductStock(final ProductStock productStock) {
        this.productStock = productStock;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(final int productCount) {
        this.productCount = productCount;
    }
}
