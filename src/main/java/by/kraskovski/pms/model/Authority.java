package by.kraskovski.pms.model;

import by.kraskovski.pms.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Description database table "authority"
 */
@Entity
public class Authority extends BaseEntity implements GrantedAuthority {

    /**
     * Authority name
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Implemented method to get Authority name
     */
    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
