package by.kraskovski.pms.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
