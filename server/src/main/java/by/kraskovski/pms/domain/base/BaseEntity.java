package by.kraskovski.pms.domain.base;

import lombok.Getter;
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
public abstract class BaseEntity {

    /**
     * General id field
     */
    @Id
    @GeneratedValue(generator = "pms-guid", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "pms-guid", strategy = "by.kraskovski.pms.domain.base.IdGenerator")
    @Getter
    private String id;

    public void setId(final String id) {
        Optional.ofNullable(id).ifPresent(s -> this.id = s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
