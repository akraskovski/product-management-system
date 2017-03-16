package by.intexsoft.model;

import by.intexsoft.model.base.BaseEntity;
import by.intexsoft.model.enums.Authority;
import javax.persistence.*;

/**
 * Description database table "role"
 */
@Entity
@Table
public class Role extends BaseEntity {

    /**
     * Role name
     */
    @Column
    @Enumerated(EnumType.STRING)
    public Authority name;
}
