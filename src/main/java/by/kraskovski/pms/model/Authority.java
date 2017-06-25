package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import by.kraskovski.pms.model.enums.AuthorityEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Description database table "authority"
 */
@Entity
public class Authority extends BaseEntity implements GrantedAuthority{

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }
}
