package example.infrastructure.db.domasupport;

import java.sql.Date;

import org.joda.time.LocalDate;
import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.DomainConverter;

@ExternalDomain
public class LocalDateConverter implements DomainConverter<LocalDate, Date> {

    @Override
    public Date fromDomainToValue(LocalDate domain) {
        return new Date(domain.toDateTimeAtStartOfDay().getMillis());
    }

    @Override
    public LocalDate fromValueToDomain(Date value) {
        return new LocalDate(value.getTime());
    }

}
