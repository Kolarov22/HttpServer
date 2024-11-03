package App;

public class HttpRequest {
    private final String method;
    private final String resourceUrl;
    private String contentType;
    private String body;

    public HttpRequest(String method, String resourceUrl, String contentType, String body) {
        this.method = method;
        this.resourceUrl = resourceUrl;
        this.contentType = contentType;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return "Method: " + method + "\nResource URL: " + resourceUrl + "\nContent Type: " + contentType + "\nBody: " + body;
    }
}
