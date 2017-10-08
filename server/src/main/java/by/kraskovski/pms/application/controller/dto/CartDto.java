package by.kraskovski.pms.application.controller.dto;

import by.kraskovski.pms.domain.model.CartProductStock;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * {@link by.kraskovski.pms.domain.model.Cart} data transfer object
 */
@Getter
@Setter
public class CartDto {
    private String id;
    private double totalCost;
    private Set<CartProductStock> cartProductStocks;
    private LocalDateTime createDate;
}
