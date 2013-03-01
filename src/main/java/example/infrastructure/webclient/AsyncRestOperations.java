package example.infrastructure.webclient;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

/**
 *
 */
public interface AsyncRestOperations {

    // GET

    /**
     * Retrieve a representation by doing a GET on the specified URL.
     * The response (if any) is converted and returned.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     */
    <T> AsyncRestOperationResult<T> getForObject(String url, Class<T> responseType, Object... uriVariables);

    /**
     * Retrieve a representation by doing a GET on the URI template.
     * The response (if any) is converted and returned.
     * <p>URI Template variables are expanded using the given map.
     * @param url the URL
     * @param responseType the type of the return value
     * @param uriVariables the map containing variables for the URI template
     * @return the converted object
     */
    <T> AsyncRestOperationResult<T> getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables);

    /**
     * Retrieve a representation by doing a GET on the URL .
     * The response (if any) is converted and returned.
     * @param url the URL
     * @param responseType the type of the return value
     * @return the converted object
     */
    <T> AsyncRestOperationResult<T> getForObject(URI url, Class<T> responseType);

    /**
     * Retrieve an entity by doing a GET on the specified URL.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(String url, Class<T> responseType, Object... uriVariables);

    /**
     * Retrieve a representation by doing a GET on the URI template.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given map.
     * @param url the URL
     * @param responseType the type of the return value
     * @param uriVariables the map containing variables for the URI template
     * @return the converted object
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables);

    /**
     * Retrieve a representation by doing a GET on the URL .
     * The response is converted and stored in an {@link ResponseEntity}.
     * @param url the URL
     * @param responseType the type of the return value
     * @return the converted object
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> getForEntity(URI url, Class<T> responseType);

    // HEAD

    /**
     * Retrieve all headers of the resource specified by the URI template.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param uriVariables the variables to expand the template
     * @return all HTTP headers of that resource
     */
    AsyncRestOperationResult<HttpHeaders> headForHeaders(String url, Object... uriVariables);

    /**
     * Retrieve all headers of the resource specified by the URI template.
     * <p>URI Template variables are expanded using the given map.
     * @param url the URL
     * @param uriVariables the map containing variables for the URI template
     * @return all HTTP headers of that resource
     */
    AsyncRestOperationResult<HttpHeaders> headForHeaders(String url, Map<String, ?> uriVariables);

    /**
     * Retrieve all headers of the resource specified by the URL.
     * @param url the URL
     * @return all HTTP headers of that resource
     */
    AsyncRestOperationResult<HttpHeaders> headForHeaders(URI url);

    // POST

    /**
     * Create a new resource by POSTing the given object to the URI template, and returns the value of the
     * <code>Location</code> header. This header typically indicates where the new resource is stored.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @return the value for the <code>Location</code> header
     * @see HttpEntity
     */
    AsyncRestOperationResult<URI> postForLocation(String url, Object request, Object... uriVariables);

    /**
     * Create a new resource by POSTing the given object to the URI template, and returns the value of the
     * <code>Location</code> header. This header typically indicates where the new resource is stored.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @return the value for the <code>Location</code> header
     * @see HttpEntity
     */
    AsyncRestOperationResult<URI> postForLocation(String url, Object request, Map<String, ?> uriVariables);

    /**
     * Create a new resource by POSTing the given object to the URL, and returns the value of the
     * <code>Location</code> header. This header typically indicates where the new resource is stored.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @return the value for the <code>Location</code> header
     * @see HttpEntity
     */
    AsyncRestOperationResult<URI> postForLocation(URI url, Object request);

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the representation found in the response.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     */
    <T> AsyncRestOperationResult<T> postForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException;

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the representation found in the response.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     */
    <T> AsyncRestOperationResult<T> postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException;

    /**
     * Create a new resource by POSTing the given object to the URL,
     * and returns the representation found in the response.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param responseType the type of the return value
     * @return the converted object
     * @see HttpEntity
     */
    <T> AsyncRestOperationResult<T> postForObject(URI url, Object request, Class<T> responseType);

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the response as {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException;

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the response as {@link HttpEntity}.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException;

    /**
     * Create a new resource by POSTing the given object to the URL,
     * and returns the response as {@link ResponseEntity}.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be POSTed, may be <code>null</code>
     * @return the converted object
     * @see HttpEntity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> postForEntity(URI url, Object request, Class<T> responseType);

    // PUT

    /**
     * Create or update a resource by PUTting the given object to the URI.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be PUT, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @see HttpEntity
     */
    AsyncRestOperationResult<Void> put(String url, Object request, Object... uriVariables);

    /**
     * Creates a new resource by PUTting the given object to URI template.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be PUT, may be <code>null</code>
     * @param uriVariables the variables to expand the template
     * @see HttpEntity
     */
    AsyncRestOperationResult<Void> put(String url, Object request, Map<String, ?> uriVariables);

    /**
     * Creates a new resource by PUTting the given object to URL.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * @param url the URL
     * @param request the Object to be PUT, may be <code>null</code>
     * @see HttpEntity
     */
    AsyncRestOperationResult<Void> put(URI url, Object request);

    // DELETE

    /**
     * Delete the resources at the specified URI.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param uriVariables the variables to expand in the template
     */
    AsyncRestOperationResult<Void> delete(String url, Object... uriVariables);

    /**
     * Delete the resources at the specified URI.
     * <p>URI Template variables are expanded using the given map.
     *
     * @param url the URL
     * @param uriVariables the variables to expand the template
     */
    AsyncRestOperationResult<Void> delete(String url, Map<String, ?> uriVariables);

    /**
     * Delete the resources at the specified URL.
     * @param url the URL
     */
    AsyncRestOperationResult<Void> delete(URI url);

    // OPTIONS

    /**
     * Return the value of the Allow header for the given URI.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param uriVariables the variables to expand in the template
     * @return the value of the allow header
     */
    AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(String url, Object... uriVariables);

    /**
     * Return the value of the Allow header for the given URI.
     * <p>URI Template variables are expanded using the given map.
     * @param url the URL
     * @param uriVariables the variables to expand in the template
     * @return the value of the allow header
     */
    AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(String url, Map<String, ?> uriVariables);

    /**
     * Return the value of the Allow header for the given URL.
     * @param url the URL
     * @return the value of the allow header
     */
    AsyncRestOperationResult<Set<HttpMethod>> optionsForAllow(URI url);

    // exchange

    /**
     * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
     * returns the response as {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand in the template
     * @return the response as entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
            Class<T> responseType, Object... uriVariables);

    /**
     * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
     * returns the response as {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand in the template
     * @return the response as entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
            Class<T> responseType, Map<String, ?> uriVariables);

    /**
     * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
     * returns the response as {@link ResponseEntity}.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
     * @param responseType the type of the return value
     * @return the response as entity
     * @since 3.0.2
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity,
            Class<T> responseType);

    /**
     * Execute the HTTP method to the given URI template, writing the given
     * request entity to the request, and returns the response as {@link ResponseEntity}.
     * The given {@link ParameterizedTypeReference} is used to pass generic type information:
     *
     * <pre class="code">
     * ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt; myBean = new ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt;() {};
     * ResponseEntity&lt;List&lt;MyBean&gt;&gt; response = template.exchange(&quot;http://example.com&quot;,HttpMethod.GET, null, myBean);
     * </pre>
     *
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the
     * request, may be {@code null}
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand in the template
     * @return the response as entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(String url,HttpMethod method, HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType, Object... uriVariables);

    /**
     * Execute the HTTP method to the given URI template, writing the given
     * request entity to the request, and returns the response as {@link ResponseEntity}.
     * The given {@link ParameterizedTypeReference} is used to pass generic type information:
     *
     * <pre class="code">
     * ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt; myBean = new ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt;() {};
     * ResponseEntity&lt;List&lt;MyBean&gt;&gt; response = template.exchange(&quot;http://example.com&quot;,HttpMethod.GET, null, myBean);
     * </pre>
     *
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand in the template
     * @return the response as entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables);

    /**
     * Execute the HTTP method to the given URI template, writing the given
     * request entity to the request, and returns the response as {@link ResponseEntity}.
     * The given {@link ParameterizedTypeReference} is used to pass generic type information:
     *
     * <pre class="code">
     * ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt; myBean = new ParameterizedTypeReference&lt;List&lt;MyBean&gt;&gt;() {};
     * ResponseEntity&lt;List&lt;MyBean&gt;&gt; response = template.exchange(&quot;http://example.com&quot;,HttpMethod.GET, null, myBean);
     * </pre>
     *
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestEntity the entity (headers and/or body) to write to the request, may be {@code null}
     * @param responseType the type of the return value
     * @return the response as entity
     * @since 3.2.0
     */
    <T> AsyncRestOperationResult<ResponseEntity<T>> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType);

    // general execution

    /**
     * Execute the HTTP method to the given URI template, preparing the request with the
     * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestCallback object that prepares the request
     * @param responseExtractor object that extracts the return value from the response
     * @param uriVariables the variables to expand in the template
     * @return an arbitrary object, as returned by the {@link ResponseExtractor}
     */
    <T> AsyncRestOperationResult<T> execute(String url, HttpMethod method, RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor, Object... uriVariables);

    /**
     * Execute the HTTP method to the given URI template, preparing the request with the
     * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
     * <p>URI Template variables are expanded using the given URI variables map.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestCallback object that prepares the request
     * @param responseExtractor object that extracts the return value from the response
     * @param uriVariables the variables to expand in the template
     * @return an arbitrary object, as returned by the {@link ResponseExtractor}
     */
    <T> AsyncRestOperationResult<T> execute(String url, HttpMethod method, RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables);

    /**
     * Execute the HTTP method to the given URL, preparing the request with the
     * {@link RequestCallback}, and reading the response with a {@link ResponseExtractor}.
     * @param url the URL
     * @param method the HTTP method (GET, POST, etc)
     * @param requestCallback object that prepares the request
     * @param responseExtractor object that extracts the return value from the response
     * @return an arbitrary object, as returned by the {@link ResponseExtractor}
     */
    <T> AsyncRestOperationResult<T> execute(URI url, HttpMethod method, RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor);

}
