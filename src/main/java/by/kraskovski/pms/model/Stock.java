package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "stock"
 */
@Entity
public class Stock extends BaseEntity {

    @OneToMany(
            mappedBy = "stock",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<ProductStock> productStocks = new HashSet<>();

    @Column(unique = true, nullable = false)
    private String specialize;

    private String address;

    private String phone;

    private double square;

    private LocalTime openTime;

    private LocalTime closeTime;

    @JsonIgnore
    public Set<ProductStock> getProductStocks() {
        return productStocks;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getSquare() {
        return square;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setProductStocks(final Set<ProductStock> productStocks) {
        this.productStocks = productStocks;
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

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}
