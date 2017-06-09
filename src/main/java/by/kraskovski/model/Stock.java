package by.kraskovski.model;

import by.kraskovski.model.base.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.List;

/**
 * Description database table "stock"
 */
@Entity
public class Stock extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String specialize;
    private String address;
    private String phone;
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
