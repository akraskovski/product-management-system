package by.kraskovski.pms.controller.dto;

import by.kraskovski.pms.domain.model.CartProductStock;
import by.kraskovski.pms.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class CartDto {

    private User user;
    private LocalDateTime createDate;
    private double totalCost;
    private Set<CartProductStock> cartProductStocks;
}
