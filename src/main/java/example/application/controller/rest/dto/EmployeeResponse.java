package example.application.controller.rest.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDateTime;
import org.msgpack.annotation.Message;

import example.application.model.dao.db.dto.Employee;

@Message
@XmlRootElement(name = "employee")
public class EmployeeResponse {

    public BigDecimal employeeId;

    public String employeeName;

    public LocalDateTime hiredate;

    public BigDecimal salary;

    public BigDecimal versionNo;

    public static EmployeeResponse from(Employee employee) {
        EmployeeResponse ret = new EmployeeResponse();
        ret.employeeId = employee.getEmployeeId();
        ret.employeeName = employee.getEmployeeName();
        ret.hiredate = employee.getHiredate();
        ret.salary = employee.getSalary();
        return ret;
    }

}
