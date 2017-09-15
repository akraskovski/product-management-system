package by.kraskovski.pms.controller.dto;

import by.kraskovski.pms.domain.model.CartProductStock;
import by.kraskovski.pms.domain.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CartDto {

    private User user;
    private LocalDateTime createDate;
    private double totalCost;
    private Set<CartProductStock> cartProductStocks;
}
