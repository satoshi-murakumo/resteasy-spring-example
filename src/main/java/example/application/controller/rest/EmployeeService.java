package example.application.controller.rest;

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

import example.application.controller.rest.dto.EmployeeListResponse;
import example.application.controller.rest.dto.EmployeeRequest;
import example.application.model.dao.db.EmployeeDao;
import example.application.model.dao.db.dto.Employee;
import example.infrastructure.transaction.ReadDb;
import example.infrastructure.transaction.ReadWriteDb;
import example.infrastructure.transaction.RequestMetaData;

@Component
@Path("employee")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private Validator validator;

    private EmployeeDao employeeDao;

    private RequestMetaData meta;

    private UriInfo uriInfo;

    private MessagePack msgPack;

    @GET
    @Path("/")
    @Transactional(value = "ReadWrite", readOnly = true)
    public EmployeeListResponse get() {
        List<Employee> list = employeeDao.selectAll();
        EmployeeListResponse response = EmployeeListResponse.from(list);
        return response;
    }


    @GET
    @Path("/")
    @ReadWriteDb
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

    @Inject
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Inject
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Inject
    public void setMeta(RequestMetaData meta) {
        this.meta = meta;
    }

    @Inject
    public void setMsgPack(MessagePack msgPack) {
        this.msgPack = msgPack;
    }

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

}
