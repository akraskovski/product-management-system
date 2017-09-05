package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "stock"
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
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
}
