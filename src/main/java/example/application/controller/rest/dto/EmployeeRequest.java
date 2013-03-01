package example.application.controller.rest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDateTime;


@XmlRootElement(name = "employee")
public class EmployeeRequest {

    @Digits(integer = 10, fraction = 0)
    public BigDecimal employeeId;

    @Size(max = 20)
    public String employeeName;

    public LocalDateTime hiredate;

    @Digits(integer = 5, fraction = 2)
    public BigDecimal salary;

}
