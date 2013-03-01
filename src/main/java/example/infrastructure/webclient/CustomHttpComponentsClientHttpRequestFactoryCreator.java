package example.infrastructure.webclient;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.AutoRetryHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class CustomHttpComponentsClientHttpRequestFactoryCreator {

    private int maxTotalConnections = 100;

    private int maxConnectionsPerRoute = 5;

    private int connectionTimeoutMilliseconds = (5 * 1000);

    private int readTimeoutMilliseconds = (60 * 1000);

    private HttpRequestRetryHandler retryHandler;

    private ServiceUnavailableRetryStrategy retryStrategy;

    public HttpComponentsClientHttpRequestFactory createFacotry() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(getMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(getMaxConnectionsPerRoute());

        DefaultHttpClient defaultClient = new DefaultHttpClient(connectionManager);
        defaultClient.setHttpRequestRetryHandler(getRetryHandler());

        AutoRetryHttpClient retryClient = new AutoRetryHttpClient(defaultClient, getRetryStrategy());

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(retryClient);
        factory.setConnectTimeout(getConnectionTimeoutMilliseconds());
        factory.setReadTimeout(getReadTimeoutMilliseconds());

        return factory;
    }

    public HttpRequestRetryHandler getRetryHandler() {
        return retryHandler;
    }

    public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
    }

    public ServiceUnavailableRetryStrategy getRetryStrategy() {
        return retryStrategy;
    }

    public void setRetryStrategy(ServiceUnavailableRetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }

    public int getReadTimeoutMilliseconds() {
        return readTimeoutMilliseconds;
    }

    public void setReadTimeoutMilliseconds(int readTimeoutMilliseconds) {
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
    }

    public int getConnectionTimeoutMilliseconds() {
        return connectionTimeoutMilliseconds;
    }

    public void setConnectionTimeoutMilliseconds(int connectionTimeoutMilliseconds) {
        this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
    }

}
