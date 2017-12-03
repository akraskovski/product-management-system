package by.kraskovski.pms.application.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

import static by.kraskovski.pms.application.validation.Patterns.PHONE_PATTERN;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Stock}
 */
@Getter
@Setter
public class StockDto {
    private String id;
    @NotNull
    private String specialize;
    private String address;
    @Pattern(regexp = PHONE_PATTERN)
    private String phone;
    private double square;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String managerId;
}
