package by.kraskovski.pms.domain.model;

import by.kraskovski.pms.domain.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * Description database table "product_stock"
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductStock extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToMany(mappedBy = "productStock", orphanRemoval = true)
    private Set<CartProductStock> cartProductStocks;

    private int productsCount;

    public ProductStock(final Product product, final Stock stock, final int productsCount) {
        this.product = product;
        this.stock = stock;
        this.productsCount = productsCount;
    }
}
