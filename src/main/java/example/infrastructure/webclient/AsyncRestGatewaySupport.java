package example.infrastructure.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class AsyncRestGatewaySupport {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private AsyncRestTemplate asyncRestTemplate;

    public AsyncRestGatewaySupport() {
        asyncRestTemplate = new AsyncRestTemplate(new RestTemplate(), new SimpleAsyncTaskExecutor());
    }

    public AsyncRestGatewaySupport(ClientHttpRequestFactory requestFactory, AsyncTaskExecutor asyncTaskExecutor) {
        asyncRestTemplate = new AsyncRestTemplate(new RestTemplate(requestFactory), asyncTaskExecutor);
    }

    public void setAsyncRestTemplate(AsyncRestTemplate asyncRestTemplate) {
        this.asyncRestTemplate = asyncRestTemplate;
    }

    public AsyncRestTemplate getAsyncRestTemplate() {
        return asyncRestTemplate;
    }

}
