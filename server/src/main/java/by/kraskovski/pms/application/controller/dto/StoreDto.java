package by.kraskovski.pms.application.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Store}
 */
@Getter
@Setter
public class StoreDto {
    private String id;
    private String name;
    private String details;
    private String address;
    private String phone;
    private String skype;
    private boolean discounts;
    private String mail;
    private String logo;
}
