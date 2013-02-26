package example.application.model.dao.rest.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class ExternalEmployee {

    public BigDecimal employeeId;

    public String employeeName;

    public Timestamp hiredate;

    public BigDecimal salary;

    public BigDecimal versionNo;

}
