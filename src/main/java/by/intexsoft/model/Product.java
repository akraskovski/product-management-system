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

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double cost;
    @Column
    private String type;
    @Column(length = 500)
    private String details;
    @Column
    private String image;

    /**
     * Product name
     */
    public String getName() {
        return name;
    }
    /**
     * Product cost
     */
    public Double getCost() {
        return cost;
    }
    /**
     * Product type
     */
    public String getType() {
        return type;
    }
    /**
     * Product details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Product image
     */
    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
