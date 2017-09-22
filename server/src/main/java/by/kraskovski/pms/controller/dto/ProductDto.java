package by.kraskovski.pms.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Product}
 */
@Getter
@Setter
public class ProductDto {

    private String id;
    private String name;
    private double cost;
    private String type;
    private String details;
    private String image;
    private double width;
    private double height;
    private double weight;
    private LocalDateTime manufactureDate;
}
