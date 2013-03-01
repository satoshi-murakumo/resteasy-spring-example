package example.infrastructure.webclient;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

public class AsyncRestOperationResult<T> {

    private final Logger logger = LoggerFactory.getLogger("example.infrastructure.webclient.AsyncRestOperationResult");

    protected int retryCount = 0;

    protected long timeout = 0;

    protected TimeUnit unit = TimeUnit.MILLISECONDS;

    protected boolean retryOnTimeout = false;

    protected boolean retryOnClientError = false;

    protected boolean retryOnServerError = false;

    protected Callable<T> callable = null;

    protected AsyncTaskExecutor asyncTaskExecutor = null;

    public AsyncRestOperationResult(Callable<T> callable, AsyncTaskExecutor asyncTaskExecutor) {
        this.callable = callable;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public AsyncRestOperationResult<T> retry(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public AsyncRestOperationResult<T> timeout(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
        return this;
    }

    public AsyncRestOperationResult<T> retryOnTimeout(boolean retryOnTimeout) {
        this.retryOnTimeout = retryOnTimeout;
        return this;
    }

    public AsyncRestOperationResult<T> retryOnClientError(boolean retryOnClientError) {
        this.retryOnClientError = retryOnClientError;
        return this;
    }

    public AsyncRestOperationResult<T> retryOnServerError(boolean retryOnServerError) {
        this.retryOnServerError = retryOnServerError;
        return this;
    }

    public T get() {

        T result = null;
        while (retryCount >= 0) {
            Future<T>  future = asyncTaskExecutor.submit(callable);

            if (timeout == 0) {
                try {
                    result = future.get();
                } catch (InterruptedException | ExecutionException e) {
                    if (!needRetry(e)) {
                        break;
                    }
                }
            } else {
                try {
                    result = future.get(timeout, unit);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    if (!needRetry(e)) {
                        break;
                    }
                }
            }

            retryCount--;
        }

        return result;
    }

    protected boolean needRetry(Exception e) {

        if (e instanceof InterruptedException) {
            logger.debug(e.getMessage());
            return true;
        } else if (e instanceof ExecutionException) {
            if (e.getCause() instanceof HttpClientErrorException) {

                logger.info(e.getCause().getMessage());
                return retryOnClientError;

            } else if (e.getCause() instanceof HttpServerErrorException) {

                logger.info(e.getCause().getMessage());
                return retryOnServerError;

            } else if (e.getCause() instanceof ResourceAccessException) {

                logger.info(e.getCause().getMessage());
                return false;

            } else if (e.getCause() instanceof RestClientException) {
                throw (RestClientException) e.getCause();
            } else {
                throw new RestClientException(e.getCause().getMessage(), e.getCause());
            }
        } else if (e instanceof TimeoutException) {
            logger.info("web request timeout : " + e.getMessage());
            return retryOnTimeout;
        } else {
            throw new RestClientException(e.getMessage(), e);
        }
    }
}
