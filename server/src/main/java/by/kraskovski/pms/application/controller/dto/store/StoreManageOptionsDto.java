package by.kraskovski.pms.application.controller.dto.store;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Store manage options dto.
 */
@Getter
@Setter
public class StoreManageOptionsDto {

    @NotBlank
    private String storeId;
    @NotBlank
    private String stockId;
}
