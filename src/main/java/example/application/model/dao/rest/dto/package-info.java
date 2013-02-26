@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = example.infrastructure.xml.SqlDateAdaptor.class, type = java.sql.Date.class),
    @XmlJavaTypeAdapter(value = example.infrastructure.xml.TimestampAdaptor.class, type = java.sql.Timestamp.class)
})
package example.application.model.dao.rest.dto;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

