package Strategy;

import App.HttpRequest;

public class PlainTextStrategy implements RequestStrategy {
    @Override
    public void handleRequest(HttpRequest request) {
        Object body = request.getBody();
        if (body != null) {
            request.setBody(body.toString());
            request.setContentType("text/plain");
        } else {
            throw new RuntimeException("Body is not a valid plain text string");
        }
    }
}