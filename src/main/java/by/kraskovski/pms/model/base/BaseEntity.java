package by.kraskovski.pms.model.base;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base abstract entity
 * Contains field id with generated type
 */
@MappedSuperclass
// TODO: Change id from integer to String
// TODO: Add custom sequence generator
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final BaseEntity that = (BaseEntity) o;

        return EqualsBuilder.reflectionEquals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
