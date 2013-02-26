package example.infrastructure.xml;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdaptor extends XmlAdapter<Date, java.sql.Date> {

    @Override
    public java.sql.Date unmarshal(Date v) throws Exception {
        return new java.sql.Date(v.getTime());
    }

    @Override
    public Date marshal(java.sql.Date v) throws Exception {
        return new java.util.Date(v.getTime());
    }

}
