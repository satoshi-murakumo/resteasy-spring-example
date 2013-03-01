@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = example.infrastructure.xml.LocalDateTimeAdapter.class, type = org.joda.time.LocalDateTime.class),
})
package example.application.controller.rest.dto;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

