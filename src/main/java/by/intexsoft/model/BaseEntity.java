package by.intexsoft.model;

import javax.persistence.*;

/**
 * Base abstract entity
 * Contains field id with generated type
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Id field for every entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
}
