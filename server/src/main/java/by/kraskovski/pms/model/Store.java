package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Description database table "store"
 */
@Entity
public class Store extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private String details;

    private String address;

    private String phone;

    private String skype;

    private boolean discounts;

    private String mail;

    private String logo;

    @ManyToMany
    @JoinTable(
            name = "stock_store",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<Stock> stockList;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkype() {
        return skype;
    }

    public boolean getDiscounts() {
        return discounts;
    }

    public String getMail() {
        return mail;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public String getLogo() {
        return logo;
    }
}
