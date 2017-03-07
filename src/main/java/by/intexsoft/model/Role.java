package by.intexsoft.model;

import by.intexsoft.model.enums.Authority;

import javax.persistence.*;

@Entity
@Table
public class Role extends BaseEntity{

    @Column
    @Enumerated(EnumType.STRING)
    public Authority name;
}
