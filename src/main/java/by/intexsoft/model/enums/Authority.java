package by.intexsoft.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_STOCK_MANAGER,
    ROLE_STORE_MANAGER,
    ANONYMOUS;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
