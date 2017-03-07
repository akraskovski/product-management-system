package by.intexsoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Store extends BaseEntity {

    @Column
    public String name;

    @ManyToMany(targetEntity = Stock.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_store",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "stock_id")}
            )
    public List<Stock> stockList;
}
