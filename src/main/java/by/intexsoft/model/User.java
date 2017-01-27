package by.intexsoft.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Description entity by database table "users".
 */
@Entity
@Table(name = "users", schema = "test")
public class User extends AbstractPersistable<Integer> {

    /**
     * User's name field.
     */
    @Column
    public String name;
}