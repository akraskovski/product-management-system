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

    @Column(unique = true, nullable = false)
    private String specialize;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private Double square;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Product.class)
    @JoinTable(
            name = "product_stock",
            joinColumns = {@JoinColumn(name = "stock_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> productList;

    /**
     * Stock specialize
     */
    public String getSpecialize() {
        return specialize;
    }
    /**
     * Stock address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Stocks contact phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Stock square
     */
    public Double getSquare() {
        return square;
    }
    /**
     * ManyToMany relation to {@link Product} entities
     */
    public List<Product> getProductList() {
        return productList;
    }
}
