package example.infrastructure.webclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;

public class CustomServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {

    private int maxRetries;
    private long retryInterval;

    public CustomServiceUnavailableRetryStrategy() {
        this(1, 50);
    }

    public CustomServiceUnavailableRetryStrategy(int maxRetries) {
        this(maxRetries, 50);
    }

    public CustomServiceUnavailableRetryStrategy(int maxRetries, long retryInterval) {
        if (maxRetries < 1) {
            throw new IllegalArgumentException("MaxRetries must be greater than 1");
        }
        if (retryInterval < 1) {
            throw new IllegalArgumentException("Retry interval must be greater than 1");
        }
        this.maxRetries = maxRetries;
        this.retryInterval = retryInterval;
    }

    public boolean retryRequest(final HttpResponse response, int executionCount, final HttpContext context) {

        return executionCount <= maxRetries &&
                (response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR ||
                 response.getStatusLine().getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE ||
                 response.getStatusLine().getStatusCode() == HttpStatus.SC_GATEWAY_TIMEOUT);
    }

    public long getRetryInterval() {
        return retryInterval;
    }
}
