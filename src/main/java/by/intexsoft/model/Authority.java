package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Description database table "authority"
 */
@Entity
@Table
public class Authority extends BaseEntity implements GrantedAuthority{

    /**
     * Authority name
     */
    @Column
    public String name;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}
