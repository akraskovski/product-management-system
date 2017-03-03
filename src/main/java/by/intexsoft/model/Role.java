package by.intexsoft.model;

import by.intexsoft.model.enums.Authority;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table
public class Role extends AbstractPersistable<Integer> {

    @Column
    @Enumerated(EnumType.STRING)
    public Authority role;

}
