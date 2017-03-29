package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @Column(unique = true, nullable = false)
    public String specialize;

    /**
     * ManyToMany relation to {@link Product} entities
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Product.class)
    @JoinTable(
            name = "product_stock",
            joinColumns = {@JoinColumn(name = "stock_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    public List<Product> productList;
}
