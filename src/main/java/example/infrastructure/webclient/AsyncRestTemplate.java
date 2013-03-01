/**
 *
 */
package example.infrastructure.webclient;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * An equivalent of RestTemplate the is able to execute the operations asynchronously. Delegates the work to
 * a {@link RestTemplate} and executes the tasks using {@link AsyncTaskExecutor}. Please note that exception {@link RestClientException} are not thrown
 * from the methods but from the futures instead
 *
 */
public class AsyncRestTemplate implements AsyncRestOperations {
    private RestOperations restTemplate;

    private AsyncTaskExecutor asyncTaskExecutor;

    public AsyncRestTemplate() {

    }

    public AsyncRestTemplate(RestOperations restTemplate, AsyncTaskExecutor asyncTaskExecutor) {
        this.restTemplate = restTemplate;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AsyncTaskExecutor getAsyncTaskExecutor() {
        return asyncTaskExecutor;
    }

    public void setAsyncTaskExecutor(AsyncTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public <T> AsyncRestOperationResult<T> getForObject(final String url, final Class<T> responseType, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.getForObject(url, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> getForObject(final String url, final Class<T> responseType, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.getForObject(url, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> getForObject(final URI url, final Class<T> responseType) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.getForObject(url, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(final String url, final Class<T> responseType,
            final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.getForEntity(url, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(final String url, final Class<T> responseType,
            final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.getForEntity(url, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(final URI url, final Class<T> responseType) {
        return new AsyncRestOperationResult<ResponseEntity<T>>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.getForEntity(url, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<HttpHeaders> headForHeaders(final String url, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<HttpHeaders>() {
            public HttpHeaders call() {
                return restTemplate.headForHeaders(url, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<HttpHeaders> headForHeaders(final String url, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<HttpHeaders>() {
            public HttpHeaders call() {
                return restTemplate.headForHeaders(url, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<HttpHeaders> headForHeaders(final URI url) {
        return new AsyncRestOperationResult<>(new Callable<HttpHeaders>() {
            public HttpHeaders call() {
                return restTemplate.headForHeaders(url);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<URI> postForLocation(final String url, final Object request, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<URI>() {
            public URI call() {
                return restTemplate.postForLocation(url, request, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<URI> postForLocation(final String url, final Object request, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<URI>() {
            public URI call() {
                return restTemplate.postForLocation(url, request, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<URI> postForLocation(final URI url, final Object request) {
        return new AsyncRestOperationResult<>(new Callable<URI>() {
            public URI call() {
                return restTemplate.postForLocation(url, request);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> postForObject(final String url, final Object request, final Class<T> responseType,
            final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.postForObject(url, request, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> postForObject(final String url, final Object request, final Class<T> responseType,
            final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.postForObject(url, request, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> postForObject(final URI url, final Object request, final Class<T> responseType) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.postForObject(url, request, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(final String url, final Object request,
            final Class<T> responseType, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.postForEntity(url, request, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(final String url, final Object request,
            final Class<T> responseType, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.postForEntity(url, request, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(final URI url, final Object request, final Class<T> responseType) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.postForEntity(url, request, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Void> put(final String url, final Object request, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.put(url, request, uriVariables);
                return null;
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Void> put(final String url, final Object request, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.put(url, request, uriVariables);
                return null;
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Void> put(final URI url, final Object request) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.put(url, request);
                return null;
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Void> delete(final String url, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.delete(url, uriVariables);
                return null;
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Void> delete(final String url, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.delete(url, uriVariables);
                return null;
            }
        },
        asyncTaskExecutor);

    }

    public AsyncRestOperationResult<Void> delete(final URI url) {
        return new AsyncRestOperationResult<>(new Callable<Void>() {
            public Void call() {
                restTemplate.delete(url);
                return null;
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(final String url, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Set<HttpMethod>>() {
            public Set<HttpMethod> call() {
                return restTemplate.optionsForAllow(url, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(final String url, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<Set<HttpMethod>>() {
            public Set<HttpMethod> call() {
                return restTemplate.optionsForAllow(url, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(final URI url) {
        return new AsyncRestOperationResult<>(new Callable<Set<HttpMethod>>() {
            public Set<HttpMethod> call() {
                return restTemplate.optionsForAllow(url);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final String url,
            final HttpMethod method, final HttpEntity<?> requestEntity,
            final Class<T> responseType, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final String url,
            final HttpMethod method, final HttpEntity<?> requestEntity,
            final Class<T> responseType, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final URI url, final HttpMethod method,
            final HttpEntity<?> requestEntity, final Class<T> responseType) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final String url,
            final HttpMethod method, final HttpEntity<?> requestEntity,
            final ParameterizedTypeReference<T> responseType, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final String url,
            final HttpMethod method, final HttpEntity<?> requestEntity,
            final ParameterizedTypeReference<T> responseType,
            final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(final URI url, final HttpMethod method,
            final HttpEntity<?> requestEntity, final ParameterizedTypeReference<T> responseType) {
        return new AsyncRestOperationResult<>(new Callable<ResponseEntity<T>>() {
            public ResponseEntity<T> call() {
                return restTemplate.exchange(url, method, requestEntity, responseType);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> execute(final String url, final HttpMethod method,
            final RequestCallback requestCallback,
            final ResponseExtractor<T> responseExtractor, final Object... uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> execute(final String url, final HttpMethod method,
            final RequestCallback requestCallback,
            final ResponseExtractor<T> responseExtractor, final Map<String, ?> uriVariables) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables);
            }
        },
        asyncTaskExecutor);
    }

    public <T> AsyncRestOperationResult<T> execute(final URI url, final HttpMethod method,
            final RequestCallback requestCallback,
            final ResponseExtractor<T> responseExtractor) {
        return new AsyncRestOperationResult<>(new Callable<T>() {
            public T call() {
                return restTemplate.execute(url, method, requestCallback, responseExtractor);
            }
        },
        asyncTaskExecutor);
    }
}
