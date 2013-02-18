/**
 *
 */
package example.web.service;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import example.web.service.response.HelloResponse;

@Component
@Path("/hello")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class HelloWorldService {

    private Validator validator;

    @Inject
    public void setValidator(Validator validator) {
        this.validator = validator;
    }


    @Path("/world/{param}")
    @GET
    public HelloResponse get(final @PathParam("param") String message) {

        HelloResponse result = new HelloResponse("hello!", message);

        return result;
    }

}
