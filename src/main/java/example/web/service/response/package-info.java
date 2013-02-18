@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = example.web.service.typeadapter.SqlDateAdaptor.class, type = java.sql.Date.class),
    @XmlJavaTypeAdapter(value = example.web.service.typeadapter.TimestampAdaptor.class, type = java.sql.Timestamp.class)
})
package example.web.service.response;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

