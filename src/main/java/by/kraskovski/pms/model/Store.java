package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Stock.class)
    @JoinTable(
            name = "stock_store",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")}
    )
    private List<Stock> stockList;

    /**
     * Store name
     */
    public String getName() {
        return name;
    }
    /**
     * Store details
     */
    public String getDetails() {
        return details;
    }
    /**
     * Store address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Store contact phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Store contact skype
     */
    public String getSkype() {
        return skype;
    }
    /**
     * Store discounts system (consist or not)
     */
    public boolean getDiscounts() {
        return discounts;
    }
    /**
     * Store contant mail
     */
    public String getMail() {
        return mail;
    }
    /**
     * ManyToMany relation to {@link Stock} entities
     */
    public List<Stock> getStockList() {
        return stockList;
    }
    /**
     * Store logo image
     */
    public String getLogo() {
        return logo;
    }
}
