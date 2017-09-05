package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Description database table "store"
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Store extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "stock_store",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<Stock> stockList = new ArrayList<>();

    private String details;

    private String address;

    private String phone;

    private String skype;

    private boolean discounts;

    private String mail;

    private String logo;
}
