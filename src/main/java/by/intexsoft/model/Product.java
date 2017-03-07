package by.intexsoft.model;

import javax.persistence.*;

@Entity
@Table
public class Product extends BaseEntity{

    @Column
    public String name;

    @Column
    public Double cost;

    @Column
    public String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    public Stock stock;
}
