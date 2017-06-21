package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cart_product_stock")
public class CartProductStock extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_stock_id")
    private ProductStock productStock;

    private int productCount;

    public Cart getCart() {
        return cart;
    }

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
