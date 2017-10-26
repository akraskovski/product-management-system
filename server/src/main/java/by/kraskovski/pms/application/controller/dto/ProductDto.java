package by.kraskovski.pms.application.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Product}
 */
@Getter
@Setter
public class ProductDto {
    private String id;

    @NotNull
    private String name;

    @NotNull
    private double cost;

    @NotNull
    private String type;

    @Size(max = 500)
    private String details;
    private String image;
    private double width;
    private double height;
    private double weight;
    private LocalDateTime manufactureDate;
}
