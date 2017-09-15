package by.kraskovski.pms.domain.model;

import by.kraskovski.pms.domain.base.BaseEntity;
import by.kraskovski.pms.domain.enums.AuthorityEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Description database table "authority"
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(final String authority) {
        this.authority = AuthorityEnum.valueOf(authority);
    }
}
