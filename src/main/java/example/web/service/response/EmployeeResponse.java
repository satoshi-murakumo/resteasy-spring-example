package example.web.service.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

import org.msgpack.annotation.Message;

import example.model.data.Employee;

@Message
@XmlRootElement(name = "employee")
public class EmployeeResponse {

    public BigDecimal employeeId;

    public String employeeName;

    public Timestamp hiredate;

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
