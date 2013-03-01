package example.application.model.dao.rest;

import javax.inject.Inject;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.support.RestGatewaySupport;

@Repository
public class EmployeeGatewayDao extends RestGatewaySupport {

    public EmployeeGatewayDao() {
    }

    @Inject
    public EmployeeGatewayDao(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    public String selectAll() {
        logger.debug("recest start");
        String ret = null;
        try {
            ret = getRestTemplate().getForObject("http://sub.common.local/timeout_test.php", String.class);
        } catch (RestClientException e) {
            logger.warn(e.getMessage());
        }
        logger.debug("recest end");
        return ret;
    }
}
