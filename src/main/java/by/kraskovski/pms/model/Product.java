package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description database table "user"
 */
@Entity
public class Product extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double cost;
    private String type;
    private String details;
    private String image;
    private Double width;
    private Double height;
    private Double weight;

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
    /**
     * Product width
     */
    public Double getWidth() {
        return width;
    }
    /**
     * Product height
     */
    public Double getHeight() {
        return height;
    }
    /**
     * Product weight
     */
    public Double getWeight() {
        return weight;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public void setWidth(final Double width) {
        this.width = width;
    }

    public void setHeight(final Double height) {
        this.height = height;
    }

    public void setWeight(final Double weight) {
        this.weight = weight;
    }
}
