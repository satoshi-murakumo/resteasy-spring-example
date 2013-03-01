package example.infrastructure.webclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.SSLException;

import org.apache.http.HttpRequest;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;


public class CustomHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {

    private ConcurrentHashMap<String, Boolean> idempotentMethods;

    public CustomHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
        super(retryCount, requestSentRetryEnabled);

        this.idempotentMethods = new ConcurrentHashMap<String, Boolean>();
        this.idempotentMethods.put("GET", Boolean.TRUE);
        this.idempotentMethods.put("HEAD", Boolean.TRUE);
        this.idempotentMethods.put("PUT", Boolean.TRUE);
        this.idempotentMethods.put("POST", Boolean.TRUE); // note. usually not idempotent.
        this.idempotentMethods.put("DELETE", Boolean.TRUE);
        this.idempotentMethods.put("OPTIONS", Boolean.TRUE);
        this.idempotentMethods.put("TRACE", Boolean.TRUE);
    }

    public CustomHttpRequestRetryHandler() {
        this(1, false);
    }

    @Override
    public boolean retryRequest(
            final IOException exception,
            int executionCount,
            final HttpContext context) {
        if (exception == null) {
            throw new IllegalArgumentException("Exception parameter may not be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        if (executionCount > getRetryCount()) {
            // Do not retry if over max retry count
            return false;
        }
        if (exception instanceof InterruptedIOException) {
            // Timeout -> retry
            return true;
        }
        if (exception instanceof UnknownHostException) {
            // Unknown host
            return false;
        }
        if (exception instanceof ConnectException) {
            // Connection refused -> retry
            return true;
        }
        if (exception instanceof SSLException) {
            // SSL handshake exception
            return false;
        }

        HttpRequest request = (HttpRequest)
            context.getAttribute(ExecutionContext.HTTP_REQUEST);

        if(requestIsAborted(request)){
            return false;
        }

        if (handleAsIdempotent(request)) {
            // Retry if the request is considered idempotent
            return true;
        }

        Boolean b = (Boolean)
            context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
        boolean sent = (b != null && b.booleanValue());

        if (!sent || isRequestSentRetryEnabled()) {
            // Retry if the request has not been sent fully or
            // if it's OK to retry methods that have been sent
            return true;
        }
        // otherwise do not retry
        return false;
    }

    @Override
    protected boolean handleAsIdempotent(final HttpRequest request) {
        String method = request.getRequestLine().getMethod().toUpperCase(Locale.US);
        Boolean b = this.idempotentMethods.get(method);
        return b != null && b.booleanValue();
    }

}
