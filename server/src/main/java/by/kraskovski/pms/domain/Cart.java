package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "cart"
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    private LocalDateTime createDate;

    private double totalCost;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<CartProductStock> cartProductStocks = new HashSet<>();

    public Cart() {
        this.createDate = LocalDateTime.now();
    }

    public Cart(final User user) {
        this.createDate = LocalDateTime.now();
        this.user = user;
    }
}
