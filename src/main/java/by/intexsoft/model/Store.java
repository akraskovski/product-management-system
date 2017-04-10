package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Description database table "store"
 */
@Entity
@Table
public class Store extends BaseEntity {

    /**
     * Store name
     */
    @Column(unique = true, nullable = false)
    public String name;

    /**
     * Store details
     */
    @Column
    public String details;

    /**
     * Store address
     */
    @Column
    public String address;

    /**
     * Store contact phone
     */
    @Column
    public String phone;

    /**
     * Store contact skype
     */
    @Column
    public String skype;

    /**
     * Store discounts system (consist or not)
     */
    @Column
    public Boolean discounts;

    /**
     * Store contant mail
     */
    @Column
    public String mail;

    /**
     * ManyToMany relation to {@link Stock} entities
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Stock.class)
    @JoinTable(
            name = "stock_store",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")}
    )
    public List<Stock> stockList;
}
