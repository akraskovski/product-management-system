package by.kraskovski.pms.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Stock}
 */
@Getter
@Setter
public class StockDto {

    private String id;
    private String specialize;
    private String address;
    private String phone;
    private double square;
    private LocalTime openTime;
    private LocalTime closeTime;
}
