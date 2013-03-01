package example.infrastructure.db;

import org.joda.time.LocalDateTime;


public interface Conventional {

    public void setCrtdate(LocalDateTime crtdate);

    public void setCrtemplcode(String crtemplcode);

    public void setCrttermid(String crttermid);

    public void setUpddate(LocalDateTime upddate);

    public void setUpdemplcode(String updemplcode);

    public void setUpdtermid(String updtermid);

}
