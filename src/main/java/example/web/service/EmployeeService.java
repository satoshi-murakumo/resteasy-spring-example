package example.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.msgpack.MessagePack;
import org.msgpack.packer.BufferPacker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import example.configuration.ReadDb;
import example.configuration.ReadWriteDb;
import example.configuration.RequestMetaData;
import example.model.dao.EmployeeDao;
import example.model.data.Employee;
import example.web.service.request.EmployeeRequest;
import example.web.service.response.EmployeeListResponse;
import example.web.service.response.EmployeeResponse;

@Component
@Path("employee")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private Validator validator;

    private EmployeeDao employeeDao;

    private RequestMetaData meta;

    private UriInfo uriInfo;

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public EmployeeService() {
    }

    @Inject
    public EmployeeService(EmployeeDao employeeDao, Validator validator, RequestMetaData metaData) {
        this.employeeDao = employeeDao;
        this.validator = validator;
        this.meta = metaData;
    }

    @GET
    @Path("/")
    @Transactional(value ="ReadWrite", readOnly = true)
    public EmployeeListResponse get() {
        List<Employee> list = employeeDao.selectAll();


        return EmployeeListResponse.from(list);
    }

    private static MessagePack msgPack;
    static {
        msgPack = new MessagePack();
        msgPack.register(EmployeeListResponse.class);
        msgPack.register(EmployeeResponse.class);
    }

    @GET
    @Path("/")
    @Transactional(value ="ReadWrite", readOnly = true)
    @Produces("application/x-msgpack")
    public Response getByMsgPack() {

        List<Employee> list = employeeDao.selectAll();
        EmployeeListResponse dto = EmployeeListResponse.from(list);

        BufferPacker packer = msgPack.createBufferPacker();
        try {
            packer.write(dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Response.ok(packer.toByteArray()).build();
    }

    @GET
    @Path("/read")
    @ReadDb
    public EmployeeListResponse getRead() {
        List<Employee> list = employeeDao.selectAll();
        return EmployeeListResponse.from(list);
    }

    @POST
    @Path("/")
    @ReadWriteDb
    public Response post(EmployeeRequest request) {
        Set<ConstraintViolation<EmployeeRequest>> violations = validator.validate(request);

        if (violations.size() > 0) {
            String message = "";
            for (ConstraintViolation<EmployeeRequest> violation : violations) {
                message = message + violation.getPropertyPath() + " | " + violation.getMessage() + "\n";
            }
            return Response.status(400).entity(message).build();

        }

        meta.setClientId("dummy client id!");
        meta.setRequestUri(uriInfo.getRequestUri().toString());

        Employee employee = employeeDao.selectById(request.employeeId);
        employee.setEmployeeName(request.employeeName);
        employee.setHiredate(request.hiredate);
        employee.setSalary(request.salary);
        employeeDao.update(employee);

        return Response.ok().build();
    }
}
