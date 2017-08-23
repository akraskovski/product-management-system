package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;

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

    public void setName(final String name) {
        this.name = name;
    }

    public void setStockList(final List<Stock> stockList) {
        this.stockList = stockList;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setSkype(final String skype) {
        this.skype = skype;
    }

    public void setDiscounts(final boolean discounts) {
        this.discounts = discounts;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public void setLogo(final String logo) {
        this.logo = logo;
    }
}
