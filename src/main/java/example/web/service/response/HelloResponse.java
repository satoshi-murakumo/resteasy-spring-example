package example.web.service.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "helloResponse")
public class HelloResponse {
    public HelloResponse() {
    }

    public HelloResponse(String attr, String message) {
        this.attr = attr;
        this.message = message;
    }

    public String attr;
    public String message;
}
