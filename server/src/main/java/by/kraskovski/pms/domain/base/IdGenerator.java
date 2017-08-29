package by.kraskovski.pms.domain.base;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 *  Entities {@link String} identifier generator
 */
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(final SessionImplementor session, final Object object) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}
