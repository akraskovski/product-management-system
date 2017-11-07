package by.kraskovski.pms.domain.model;

import by.kraskovski.pms.domain.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "stock"
 */
@Entity
@Getter
@Setter
public class Stock extends BaseEntity {

    @OneToMany(
            mappedBy = "stock",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<ProductStock> productStocks = new HashSet<>();

    @Column(unique = true, nullable = false)
    private String specialize;

    @ManyToOne
    @JoinColumn(name = "manager")
    private User manager;

    private String address;

    private String phone;

    private double square;

    private LocalTime openTime;

    private LocalTime closeTime;
}
