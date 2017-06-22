package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, orphanRemoval = true)
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

    public void setSpecialize(final String specialize) {
        this.specialize = specialize;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setSquare(final double square) {
        this.square = square;
    }

    public void setProductStocks(final Set<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }
}
