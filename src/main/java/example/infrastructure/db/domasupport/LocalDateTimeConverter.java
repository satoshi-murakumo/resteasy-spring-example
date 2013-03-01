package example.infrastructure.db.domasupport;

import java.sql.Timestamp;

import org.joda.time.LocalDateTime;
import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

@ExternalDomain
public class LocalDateTimeConverter implements DomainConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp fromDomainToValue(LocalDateTime domain) {
        return new Timestamp(domain.toDateTime().getMillis());
    }

    @Override
    public LocalDateTime fromValueToDomain(Timestamp value) {
        return new LocalDateTime(value.getTime());
    }

}
