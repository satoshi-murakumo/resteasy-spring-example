package example.application.controller.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.msgpack.annotation.Message;

import example.application.model.dao.db.dto.Employee;

@XmlRootElement(name = "employees")
@Message
public class EmployeeListResponse {

    public EmployeeResponse[] employee;

    public static EmployeeListResponse from(List<Employee> employees) {
        EmployeeListResponse ret = new EmployeeListResponse();
        ret.employee = new EmployeeResponse[employees.size()];
        for (int i = 0; i < ret.employee.length; i++) {
               ret.employee[i] = EmployeeResponse.from(employees.get(i));
        }
        return ret;
    }
}
