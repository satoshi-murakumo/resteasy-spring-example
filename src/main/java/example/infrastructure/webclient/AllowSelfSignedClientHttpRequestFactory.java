package example.infrastructure.webclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class AllowSelfSignedClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);

    public AllowSelfSignedClientHttpRequestFactory() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        try {
            schemeRegistry.register(new Scheme("https", 443, new SSLSocketFactory(new TrustSelfSignedStrategy(),
                    new AllowAllHostnameVerifier())));
        } catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new SSLInitializationException("faild to create SSLSocketFactory with TrustSelfSignedStrategy", e);
        }

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

        setHttpClient(new DefaultHttpClient(connectionManager));
        setReadTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS);
    }

    public AllowSelfSignedClientHttpRequestFactory(HttpClient httpClient) {
        super(httpClient);
    }

}
