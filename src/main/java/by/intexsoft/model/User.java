package by.intexsoft.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.*;

/**
 * Description entity by database table "user"
 */
@Entity
@Table
public class User extends AbstractPersistable<Integer> {

    /**
     * User's name field
     */
    @Column
    public String username;

    /**
     * User's password field
     */
    @Column
    public String password;
}