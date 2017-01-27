package by.intexsoft.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "test")
public class User extends AbstractPersistable<Integer> {

    @Column
    private String name;
}