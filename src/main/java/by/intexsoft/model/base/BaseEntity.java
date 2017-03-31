package by.intexsoft.model.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
