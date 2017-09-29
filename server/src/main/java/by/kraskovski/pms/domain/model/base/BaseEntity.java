package by.kraskovski.pms.domain.model.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base abstract entity
 * Contains field id with generated type
 */
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {

    /**
     * General Id field
     * Uses custom Id generator
     */
    @Id
    @GeneratedValue(generator = "pms-guid", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "pms-guid", strategy = "by.kraskovski.pms.domain.model.base.IdGenerator")
    @Getter
    @Setter
    private String id;
}
