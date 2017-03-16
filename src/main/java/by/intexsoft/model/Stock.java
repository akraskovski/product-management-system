package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Description database table "stock"
 */
@Entity
@Table
public class Stock extends BaseEntity {

    /**
     * Stock specialize
     */
    @Column
    public String specialize;

    /**
     * ManyToMany relation to {@link Product} entities
     */
    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_stock",
            joinColumns = {@JoinColumn(name = "stock_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    public List<Product> productList;
}
