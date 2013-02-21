package example.web.service.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.msgpack.annotation.Message;

import example.model.data.Employee;

@XmlRootElement(name = "employees")
@Message
public class EmployeeListResponse {

    @XmlElement(name = "employee")
    public EmployeeResponse[] employees;

    public static EmployeeListResponse from(List<Employee> employees) {
        EmployeeListResponse ret = new EmployeeListResponse();
        ret.employees = new EmployeeResponse[employees.size()];
        for (int i = 0; i < ret.employees.length; i++) {
               ret.employees[i] = EmployeeResponse.from(employees.get(i));
        }
        return ret;
    }
}
