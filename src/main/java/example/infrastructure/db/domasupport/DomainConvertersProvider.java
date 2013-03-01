package example.infrastructure.db.domasupport;

import org.seasar.doma.DomainConverters;

@DomainConverters({
    LocalDateTimeConverter.class,
    LocalDateConverter.class
})
public class DomainConvertersProvider {

}
