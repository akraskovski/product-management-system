package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description database table "user"
 */
@Entity
@Table
public class Product extends BaseEntity {

    /**
     * Product name
     */
    @Column(unique = true, nullable = false)
    public String name;

    /**
     * Product cost
     */
    @Column(nullable = false)
    public Double cost;

    /**
     * Product type
     */
    @Column(nullable = false)
    public String type;
}
