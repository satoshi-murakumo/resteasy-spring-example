package example.infrastructure.transaction;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestMetaData {

    private String clientId;
    private String requestUri;

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getRequestUri() {
        return requestUri;
    }
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

}
