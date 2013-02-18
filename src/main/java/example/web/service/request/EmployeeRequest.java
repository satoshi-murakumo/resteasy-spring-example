package example.web.service.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employee")
public class EmployeeRequest {

    public String employeeName;

    public Timestamp hiredate;

    public BigDecimal salary;

}
