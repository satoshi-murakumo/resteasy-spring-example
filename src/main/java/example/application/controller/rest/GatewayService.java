package example.application.controller.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import example.application.controller.rest.dto.EmployeeListResponse;
import example.application.controller.rest.dto.EmployeeResponse;
import example.application.model.dao.rest.EmployeeGatewayDao;
import example.application.model.dao.rest.dto.EmployeeGatewayResponse;
import example.application.model.dao.rest.dto.ExternalEmployee;

@Component
@Path("employeegateway")
@Produces(MediaType.APPLICATION_XML)
public class GatewayService {

    private static Logger logger = LoggerFactory.getLogger(GatewayService.class);

    EmployeeGatewayDao gatewayDao;

    public GatewayService() {
    }

    @Inject
    public GatewayService(EmployeeGatewayDao dao) {
        this.gatewayDao = dao;
    }

    @GET
    @Path("/")
    public EmployeeListResponse get() {
        logger.debug("enter employeegateway - !");

        EmployeeGatewayResponse gatewayresponse = gatewayDao.selectAll();
        logger.debug("external response retrieved - !");

        List<ExternalEmployee> empList = gatewayresponse.employee;

        EmployeeResponse[] responseList = new EmployeeResponse[empList.size()];
        for (int i = 0; i < empList.size(); i++) {
            EmployeeResponse dest = new EmployeeResponse();
            ExternalEmployee src = empList.get(i);
            dest.employeeId = src.employeeId;
            dest.employeeName = src.employeeName;
            dest.hiredate = src.hiredate;
            dest.salary = src.salary;
            dest.versionNo = src.versionNo;
            responseList[i] = dest;
        }
        EmployeeListResponse response = new EmployeeListResponse();
        response.employee = responseList;
        logger.debug("type conversion - !");

        return response;
    }

}
