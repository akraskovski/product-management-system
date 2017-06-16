package by.kraskovski.pms.model.base;

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
     * General id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public void setId(final int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public int getId() {
        return id;
    }
}
