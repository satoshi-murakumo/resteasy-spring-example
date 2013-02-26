package example.infrastructure.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdaptor extends XmlAdapter<java.util.Date, java.sql.Timestamp> {

    @Override
    public java.util.Date marshal(java.sql.Timestamp v) {
        return new java.util.Date(v.getTime());
    }

    @Override
    public java.sql.Timestamp unmarshal(java.util.Date v) throws Exception {
        return new java.sql.Timestamp(v.getTime());
    }

}
