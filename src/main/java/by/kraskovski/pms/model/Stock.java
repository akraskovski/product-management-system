package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "stock"
 */
@Entity
@Table
public class Stock extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String specialize;
    private String address;
    private String phone;
    private Double square;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<ProductStock> productStocks = new HashSet<>();

    /**
     * ManyToMany relation to {@link Product} entities
     */
    public Set<ProductStock> getProductStocks() {
        return productStocks;
    }

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

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public void setProductStocks(Set<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }
}
