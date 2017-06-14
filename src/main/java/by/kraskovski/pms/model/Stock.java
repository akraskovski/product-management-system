package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "stock"
 */
@Entity
public class Stock extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String specialize;
    private String address;
    private String phone;
    private double square;

    @OneToMany(
            mappedBy = "stock",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<ProductStock> productStocks = new HashSet<>();

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
    public double getSquare() {
        return square;
    }

    @JsonIgnore
    public Set<ProductStock> getProductStocks() {
        return productStocks;
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

    public void setSquare(double square) {
        this.square = square;
    }

    public void setProductStocks(Set<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }
}
