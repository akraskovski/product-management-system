package by.intexsoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Stock extends BaseEntity{

    @Column
    public String specialize;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "stock"
    )
    public List<Product> productList;

    @ManyToMany(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_store",
            joinColumns = {@JoinColumn(name = "stock_id")},
            inverseJoinColumns = {@JoinColumn(name = "store_id")}
    )
    public List<Store> storeList;
}
