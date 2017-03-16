package by.intexsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Description database table "user"
 */
@Entity
@Table
public class Product extends BaseEntity{

    /**
     * Product name
     */
    @Column
    public String name;

    /**
     * Product cost
     */
    @Column
    public Double cost;

    /**
     * Product type
     */
    @Column
    public String type;

    /**
     * ManyToMany relation to {@link Stock} entities
     */
    @ManyToMany(targetEntity = Stock.class, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "product_stock",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")}
    )
    private List<Stock> stockList;
}
