package example.web.service.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import example.model.data.Employee;

@XmlRootElement(name = "employees")
public class EmployeeListResponse {

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
