package by.kraskovski.pms.application.controller.dto.store;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static by.kraskovski.pms.application.validation.Patterns.MAIL_PATTERN;
import static by.kraskovski.pms.application.validation.Patterns.PHONE_PATTERN;

/**
 * Data transfer object for {@link by.kraskovski.pms.domain.model.Store}
 */
@Getter
@Setter
public class StoreDto {
    private String id;

    @NotNull
    private String name;

    @Size(max = 500)
    private String details;
    private String address;

    @Pattern(regexp = PHONE_PATTERN)
    private String phone;
    private String skype;
    private boolean discounts;

    @Pattern(regexp = MAIL_PATTERN)
    private String mail;
    private String logo;
}
