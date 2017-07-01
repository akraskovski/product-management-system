package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
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

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    public Authority(final AuthorityEnum authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }
}
