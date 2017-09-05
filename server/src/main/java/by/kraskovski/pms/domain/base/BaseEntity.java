package by.kraskovski.pms.domain.base;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Optional;

/**
 * Base abstract entity
 * Contains field id with generated type
 */
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {

    /**
     * General id field
     */
    @Id
    @GeneratedValue(generator = "pms-guid", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "pms-guid", strategy = "by.kraskovski.pms.domain.base.IdGenerator")
    private String id;

    public void setId(final String id) {
        Optional.ofNullable(id).ifPresent(s -> this.id = s);
    }

    public String getId() {
        return id;
    }
}
