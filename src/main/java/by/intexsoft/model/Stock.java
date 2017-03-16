package by.intexsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Description database table "stock"
 */
@Entity
@Table
public class Stock extends BaseEntity{

    /**
     * Stock specialize
     */
    @Column
    public String specialize;

    /**
     * OneToMany relation to {@link Product} entities
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "stock"
    )
    public List<Product> productList;

    /**
     * ManyToMany relation to {@link Store} entities
     */
    @ManyToMany(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_store",
            joinColumns = {@JoinColumn(name = "stock_id")},
            inverseJoinColumns = {@JoinColumn(name = "store_id")}
    )
    @JsonIgnore
    public List<Store> storeList;
}
