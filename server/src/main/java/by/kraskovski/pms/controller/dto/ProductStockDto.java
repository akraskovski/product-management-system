package by.kraskovski.pms.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockDto {

    private ProductDto product;
    private int productsCount;
}
