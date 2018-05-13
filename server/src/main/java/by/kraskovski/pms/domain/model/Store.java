package by.kraskovski.pms.domain.model;

import by.kraskovski.pms.domain.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Store extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "stock_store",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> stockList = new ArrayList<>();
    @Column(length = 1000)
    private String details;
    private String address;
    private String phone;
    private String skype;
    private boolean discounts;
    private String mail;
    private String logo;
}
