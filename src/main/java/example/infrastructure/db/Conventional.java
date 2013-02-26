package example.infrastructure.db;

import java.sql.Timestamp;


public interface Conventional {

    public void setCrtdate(Timestamp crtdate);

    public void setCrtemplcode(String crtemplcode);

    public void setCrttermid(String crttermid);

    public void setUpddate(Timestamp upddate);

    public void setUpdemplcode(String updemplcode);

    public void setUpdtermid(String updtermid);

}
