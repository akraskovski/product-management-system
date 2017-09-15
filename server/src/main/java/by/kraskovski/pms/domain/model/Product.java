package by.kraskovski.pms.domain.model;

import by.kraskovski.pms.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "user"
 */
@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double cost;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProductStock> productStocks = new HashSet<>();

    private LocalDateTime manufactureDate;
    private String type;
    private String details;
    private String image;
    private double width;
    private double height;
    private double weight;
}
