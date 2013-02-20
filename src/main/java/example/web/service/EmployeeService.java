package example.web.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import example.model.dao.EmployeeDao;
import example.model.data.Employee;
import example.web.service.request.EmployeeRequest;
import example.web.service.response.EmployeeListResponse;

@Component
@Path("employee")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private Validator validator;

    @Inject
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    private EmployeeDao employeeDao;

    @Inject
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @GET
    @Path("/")
    @Transactional(value = "ReadWrite", rollbackFor=java.lang.Exception.class)
    public EmployeeListResponse get() {
        logger.debug("enter get");
        List<Employee> list = employeeDao.selectAll();
        return EmployeeListResponse.from(list);
    }

    @GET
    @Path("/read")
    @Transactional(value = "ReadOnly", rollbackFor=java.lang.Exception.class)
    public EmployeeListResponse getRead() {
        List<Employee> list = employeeDao.selectAll();
        return EmployeeListResponse.from(list);
    }

    @POST
    @Path("/")
    @Transactional(value = "ReadWrite", rollbackFor=java.lang.Exception.class)
    public Response post(EmployeeRequest request) {
        return Response.ok().build();
    }
}
