package example.application.model.dao.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employees")
public class EmployeeGatewayResponse {

    public List<ExternalEmployee> employee;

}
