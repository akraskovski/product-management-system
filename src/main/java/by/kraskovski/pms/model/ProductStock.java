package by.kraskovski.pms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductStock implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", unique = true)
    public Product product;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    public Stock stock;
    public int productsCount;
}
