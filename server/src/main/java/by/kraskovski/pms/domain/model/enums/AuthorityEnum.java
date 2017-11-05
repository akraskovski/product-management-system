package by.kraskovski.pms.domain.model.enums;

import lombok.Getter;

/**
 * {@link by.kraskovski.pms.domain.model.Authority} available types
 */
public enum AuthorityEnum {

    ROLE_ADMIN("Administrator"),
    ROLE_STOCK_MANAGER("Stock manager"),
    ROLE_STORE_MANAGER("Store manager"),
    ROLE_USER("User");

    @Getter
    private String description;

    AuthorityEnum(final String description) {
        this.description = description;
    }
}
