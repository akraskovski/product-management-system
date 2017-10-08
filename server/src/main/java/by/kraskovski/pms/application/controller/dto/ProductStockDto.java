package by.kraskovski.pms.application.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.ProductStock}
 */
@Getter
@Setter
public class ProductStockDto {
    private ProductDto product;
    private int productsCount;
}
