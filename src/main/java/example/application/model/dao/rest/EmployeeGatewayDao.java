package example.application.model.dao.rest;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.support.RestGatewaySupport;

import example.application.model.dao.rest.dto.EmployeeGatewayResponse;

@Repository
public class EmployeeGatewayDao extends RestGatewaySupport {

    public EmployeeGatewayResponse selectAll() {
        return this.getRestTemplate().getForObject("http://localhost:9080/resteasy-spring-example/api/employee",
                EmployeeGatewayResponse.class);
    }
}
